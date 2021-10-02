/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

package jax.features.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class EnumSourceTest {
    private StringCounter stringCounter = new StringCounter();

    @ParameterizedTest
    @EnumSource(Text.class)
    void testCount(Text text) {
        assertEquals(3, stringCounter.count(text.value()));
    }

    @ParameterizedTest
    @EnumSource(value = Text.class, names = {"JUNIT_IN_ACTION", "THREE_PARAMETERS"})
    void testIncludeInstancesInEnum(Text text) {
        assertEquals(3, stringCounter.count(text.value()));
    }

    @ParameterizedTest
    @EnumSource(value = Text.class, mode = EXCLUDE, names = {"THREE_PARAMETERS"})
    void testExcludedInstancesInEnum(Text text) {
        assertEquals(3, stringCounter.count(text.value()));
    }

    enum Text {
        JUNIT_IN_ACTION("JUnit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");

        private final String text;

        Text(String text) {
            this.text = text;
        }

        public String value() {
            return text;
        }
    }
}
