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
 * CuteJson is a fast stateless JSON formatter.
 * There is no validation whether source JSON is valid or not.
 *
 * @author Waldemar Sojka
 */
public class CuteJson {

    private static final String TAB = StringUtils.repeat(' ', 4);

    /**
     * Formats JSON string without any validation.
     *
     * @param source source JSON string
     * @return formatted JSON string
     */
    public static String format(final String source) {
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
                        sb.append("\n" + StringUtils.repeat(TAB, indent));
                    }
                    break;
                case '{':
                    sb.append(source.charAt(i));
                    indent++;
                    sb.append("\n" + StringUtils.repeat(TAB, indent));
                    break;
                case ']':
                    if (isRegularChar) {
                        sb.append(source.charAt(i));
                    } else {
                        indent--;
                        sb.append("\n" + StringUtils.repeat(TAB, indent));
                        sb.append(source.charAt(i));
                    }
                    break;
                case '}':
                    indent--;
                    sb.append("\n" + StringUtils.repeat(TAB, indent));
                    sb.append(source.charAt(i));
                    break;
                case ',':
                    sb.append(source.charAt(i));
                    if (!isRegularChar)
                        sb.append("\n" + StringUtils.repeat(TAB, indent));
                    break;
                default:
                    sb.append(source.charAt(i));
                    break;
            }
        }
        return sb.toString();
    }
}
