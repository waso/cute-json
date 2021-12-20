# cute-json
Fast json formatter for Java

## Description

`cute-json` is a small java library that formats JSON string.
There is no validation whether input JSON is valid or not, but invalid input generates incorrectly formatted results.
`cute-json` is formatting JSON using pure String manipulation, there is no object instantiation at any point. 

## Getting started

Maven dependency:

    <dependency>
        <groupId>com.wsojka</groupId>
        <artifactId>cute-json</artifactId>
        <version>0.2</version>
    </dependency>

## Examples

### Formatting with default settings 

    String formattedJson = CuteJson.format("{\"name\":\"john\"}");

### Custom formatting configuration using space character
    String source = "{\"name\":\"john\"}";
    CuteJson cuteJson = CuteJsonBuilder
            .create()
            .withIndentationPolicy(IndentationPolicy.SPACES)
            .withSpaceCount(6)
            .build();
    String formatted = cuteJson.read(source).format();

### Custom formatting configuration using tab character

    String source = "{\"name\":\"john\"}";
    CuteJson cuteJson = CuteJsonBuilder
            .create()
            .withIndentationPolicy(IndentationPolicy.TABS)
            .build();
    String formatted = cuteJson.read(source).format();

## Credits

Current list of maintainters:

[Waldemar Sojka](https://twitter.com/WaldemarSojka)

## License

`cute-json` is distributed under [MIT license](https://github.com/waso/cute-json/blob/develop/LICENSE.txt)