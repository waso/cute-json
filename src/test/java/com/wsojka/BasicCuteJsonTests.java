/*
 * MIT License
 *
 * Copyright (c) 2021 Waldemar Sojka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.wsojka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicCuteJsonTests {

    @Test
    public void formatSimpleJsonTest() {
        String source = "{\"name\":\"john\"}";
        String expectedJson = "{\n" +
                "    \"name\": \"john\"\n" +
                "}";
        String actualJson = CuteJson.format(source);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void customSettings6SpacesTest() {
        String source = "{\"name\":\"john\"}";
        String expectedJson = "{\n" +
                "      \"name\": \"john\"\n" +
                "}";
        CuteJson cuteJson = CuteJsonBuilder
                .create()
                .withIndentationPolicy(IndentationPolicy.SPACES)
                .withSpaceCount(6)
                .build();

        String actualJson = cuteJson.read(source).format();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void customSettings1SpaceTest() {
        String source = "{\"name\":\"john\"}";
        String expectedJson = "{\n" +
                " \"name\": \"john\"\n" +
                "}";
        CuteJson cuteJson = CuteJsonBuilder
                .create()
                .withIndentationPolicy(IndentationPolicy.SPACES)
                .withSpaceCount(1)
                .build();

        String actualJson = cuteJson.read(source).format();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void customSettings4SpacesExtendedTest() {
        String source = "{\"person\":{\"age\":20,\"address\":{\"country\":\"Poland\"}}}";
        String expectedJson = "{\n" +
                "    \"person\": {\n" +
                "        \"age\": 20,\n" +
                "        \"address\": {\n" +
                "            \"country\": \"Poland\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        CuteJson cuteJson = CuteJsonBuilder
                .create()
                .withIndentationPolicy(IndentationPolicy.SPACES)
                .withSpaceCount(4)
                .build();

        String actualJson = cuteJson.read(source).format();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void customSettingsWithTabTest() {
        String source = "{\"name\":\"john\"}";
        String expectedJson = "{\n" +
                "\t\"name\": \"john\"\n" +
                "}";
        CuteJson cuteJson = CuteJsonBuilder
                .create()
                .withIndentationPolicy(IndentationPolicy.TABS)
                .build();

        String actualJson = cuteJson.read(source).format();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void customSettingsWithTabExtendedTest() {
        String source = "{\"person\":{\"age\":20,\"address\":{\"country\":\"Poland\"}}}";
        String expectedJson = "{\n" +
                "\t\"person\": {\n" +
                "\t\t\"age\": 20,\n" +
                "\t\t\"address\": {\n" +
                "\t\t\t\"country\": \"Poland\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        CuteJson cuteJson = CuteJsonBuilder
                .create()
                .withIndentationPolicy(IndentationPolicy.TABS)
                .build();

        String actualJson = cuteJson.read(source).format();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void customSettingsBuilderTest() {
        String source = "{\"name\":\"john\"}";
        String expectedJson = "{\n" +
                "      \"name\": \"john\"\n" +
                "}";
        CuteJsonBuilder cuteJsonBuilder = CuteJsonBuilder.create();
        cuteJsonBuilder.withIndentationPolicy(IndentationPolicy.SPACES);
        cuteJsonBuilder.withSpaceCount(6);

        CuteJson cuteJson = cuteJsonBuilder.build();
        String actualJson = cuteJson.read(source).format();

        assertEquals(expectedJson, actualJson);
    }
}
