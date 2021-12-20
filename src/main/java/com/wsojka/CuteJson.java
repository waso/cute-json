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

import org.apache.commons.lang3.StringUtils;

/**
 * {@code CuteJson} is a fast stateless JSON formatter.
 * There is no validation whether source JSON is valid or not.
 *
 * @author Waldemar Sojka
 */
public class CuteJson {

    private static final char TAB = '\t';

    private static final char SPACE = ' ';

    private final Configuration configuration;

    private String json;

    /**
     * Constructs new instance of {@code CuteJson} with provided configuration
     *
     * @param configuration configuration class
     */
    public CuteJson(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Reads source json
     *
     * @param json json to format
     * @return instance of CuteJson
     */
    public CuteJson read(String json) {
        this.json = json;
        return this;
    }

    /**
     * Formats json provided via {@link #read(String)} method.
     * Throws {@code IllegalArgumentException} when configuration is null.
     *
     * @return formatted JSON string
     */
    public String format() {
        return formatJson(this.json, this.configuration);
    }

    /**
     * Formats JSON string using default settings.
     * For {@code null} input String, return {@code null}.
     * Throws {@code IllegalArgumentException} when configuration is null.
     *
     * @param source source JSON string
     * @return formatted JSON string
     */
    public static String format(final String source) {
        return formatJson(source, new Configuration());
    }

    private static String formatJson(final String source, Configuration conf) {
        if (source == null)
            return null;
        if (source.isEmpty())
            return source;
        if (conf == null)
            throw new IllegalArgumentException("invalid or missing configuration");
        StringBuilder sb = new StringBuilder();
        boolean isRegularChar = false;
        boolean ignoreWhiteSpace = true;
        int indent = 0;
        for (int i = 0; i < source.length(); i++) {
            switch (source.charAt(i)) {
                case '\t':
                case ' ':
                case '\n':
                    if (!ignoreWhiteSpace)
                        sb.append(source.charAt(i));
                    break;
                case ':':
                    if (isRegularChar)
                        sb.append(source.charAt(i));
                    else
                        sb.append(source.charAt(i) + " ");
                    break;
                case '\"':
                    sb.append(source.charAt(i));
                    if (i - 1 >= 0 && source.charAt(i - 1) != '\\') {
                        isRegularChar = !isRegularChar;
                        ignoreWhiteSpace = !ignoreWhiteSpace;
                    }
                    break;
                case '[':
                    if (isRegularChar) {
                        sb.append(source.charAt(i));
                    } else {
                        sb.append(source.charAt(i));
                        indent++;
                        sb.append("\n" + addIndent(indent, conf));
                    }
                    break;
                case '{':
                    sb.append(source.charAt(i));
                    indent++;
                    sb.append("\n" + addIndent(indent, conf));
                    break;
                case ']':
                    if (isRegularChar) {
                        sb.append(source.charAt(i));
                    } else {
                        indent--;
                        sb.append("\n" + addIndent(indent, conf));
                        sb.append(source.charAt(i));
                    }
                    break;
                case '}':
                    indent--;
                    sb.append("\n" + addIndent(indent, conf));
                    sb.append(source.charAt(i));
                    break;
                case ',':
                    sb.append(source.charAt(i));
                    if (!isRegularChar)
                        sb.append("\n" + addIndent(indent, conf));
                    break;
                default:
                    sb.append(source.charAt(i));
                    break;
            }
        }
        return sb.toString();
    }

    private static String addIndent(int indent, Configuration conf) {
        if (conf.getIndentationPolicy() == IndentationPolicy.TABS)
            return StringUtils.repeat(TAB, indent);
        else
            return StringUtils.repeat(StringUtils.repeat(SPACE, conf.getSpaceCount()), indent);
    }
}
