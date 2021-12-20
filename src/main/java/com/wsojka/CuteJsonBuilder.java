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

/**
 * Builder class to build instance of {@code CuteJson} with custom formatting settings.
 *
 * @author Waldemar Sojka
 */
class CuteJsonBuilder {

    private final Configuration configuration;

    public CuteJsonBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Static method to create new instance of {@code CuteJsonBuilder}
     *
     * @return
     */
    public static CuteJsonBuilder create() {
        Configuration configuration = new Configuration();
        return new CuteJsonBuilder(configuration);
    }

    /**
     * Sets indentation policy. Throws {@code IllegalArgumentException} when policy is null.
     *
     * @param indentationPolicy indentation policy. For details please refer to {@link IndentationPolicy}
     * @return instance of {@code CuteJsonBuilder}
     */
    public CuteJsonBuilder withIndentationPolicy(IndentationPolicy indentationPolicy) {
        if (indentationPolicy == null)
            throw new IllegalArgumentException("invalid indentationPolicy provided");
        this.configuration.setIndentationPolicy(indentationPolicy);
        return this;
    }

    /**
     * Sets space count for {@code IndentationPolicy.SPACE} policy.
     * Throws {@code IllegalArgumentException} if space count is less than 0.
     *
     * @param spaceCount space count
     * @return instance of {@code CuteJsonBuilder}
     */
    public CuteJsonBuilder withSpaceCount(int spaceCount) {
        if (spaceCount < 0)
            throw new IllegalArgumentException("invalid space count provided");
        this.configuration.setSpaceCount(spaceCount);
        return this;
    }

    /**
     * Builds instance of {@code CuteJson}
     *
     * @return instance of {@code CuteJson}
     */
    public CuteJson build() {
        return new CuteJson(this.configuration);
    }

}
