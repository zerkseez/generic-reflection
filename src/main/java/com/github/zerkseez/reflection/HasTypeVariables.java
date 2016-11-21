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

import java.util.List;

public interface HasTypeVariables {
    /**
     * Checks if this object has type variables
     * 
     * @return true if this object has type variables; false otherwise
     */
    boolean hasTypeVariables();

    /**
     * Gets all type variables, including inherited ones
     * 
     * @return All type variables, including inherited ones
     */
    List<TypeVariableInfo> getTypeVariables();

    /**
     * Checks if this object has directly declared type variables
     * 
     * @return true if this object has directly declared type variables; false
     *         otherwise
     */
    boolean hasDeclaredTypeVariables();

    /**
     * Gets all directly declared type variables
     * 
     * @return All directly declared type variables
     */
    List<TypeVariableInfo> getDeclaredTypeVariables();
}
