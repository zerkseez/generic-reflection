/*******************************************************************************
 * Copyright 2016 Xerxes Tsang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.github.zerkseez.reflection;

import java.lang.reflect.Parameter;

public class ParameterInfo {
    private final Parameter parameter;
    private final TypeInfo<?> actualType;

    public ParameterInfo(final Parameter parameter, final TypeInfo<?> actualType) {
        this.parameter = parameter;
        this.actualType = actualType;
    }

    /**
     * Gets the underlying java.lang.reflect.Parameter object
     * 
     * @return The underlying java.lang.reflect.Parameter object
     */
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Gets the name of this parameter
     * 
     * @return The name of this parameter
     */
    public String getName() {
        return getParameter().getName();
    }

    /**
     * Gets the type of this parameter
     * 
     * @return The type of this parameter
     */
    public TypeInfo<?> getType() {
        return actualType;
    }
}
