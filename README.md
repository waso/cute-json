# cute-json
Fast json formatter for Java

## Description

`cute-json` is a small java library that formats JSON string.
There is no validation whether input JSON is valid or not, but invalid input generates incorrectly formatted results.
`cute-json` is formatting JSON using pure String manipulation, there is no object instantiation at any point. 

## Getting started

Maven dependency:
TBD...

## Example

    String formattedJson = CuteJson.format("{\"name\":\"john\"}");

## Credits

Current list of maintainters:

[Waldemar Sojka](https://twitter.com/WaldemarSojka)

## License

`cute-json` is distributed under [MIT license](https://github.com/waso/cute-json/blob/develop/LICENSE.txt)