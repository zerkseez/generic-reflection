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

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * This class consists exclusively of static method for obtaining TypeInfo
 * objects by class name or java.lang.reflect.Type object.
 * 
 * @author xerxes
 */
public final class Reflection {
    /**
     * Obtains the corresponding TypeInfo object for the specified
     * java.lang.reflect.Type object
     * 
     * @param type
     *            The java.lang.reflect.Type object for which type information
     *            should be parsed
     * @return The corresponding TypeInfo object
     */
    public static TypeInfo<?> getTypeInfo(final Type type) {
        if (type == null) {
            return null;
        }
        if (type instanceof Class) {
            return ClassInfo.of((Class<?>) type);
        }
        else if (type instanceof GenericArrayType) {
            return new GenericArrayInfo((GenericArrayType) type);
        }
        else if (type instanceof ParameterizedType) {
            return new ParameterizedTypeInfo((ParameterizedType) type);
        }
        else if (type instanceof TypeVariable) {
            return new TypeVariableInfo((TypeVariable<?>) type);
        }
        else if (type instanceof WildcardType) {
            return new WildcardTypeInfo((WildcardType) type);
        }
        throw new ReflectionException(String.format("%s is not supported", type.getClass().getName()));
    }

    /**
     * Obtains the corresponding TypeInfo object for the specified class
     * 
     * @param className
     *            The class name of the type for which type information should
     *            be parsed
     * @return The corresponding TypeInfo object
     */
    public static TypeInfo<?> getTypeInfo(final String className) {
        try {
            return getTypeInfo(Class.forName(className));
        }
        catch (ClassNotFoundException e) {
            throw new ReflectionException(String.format("%s is not found", className));
        }
    }

    private Reflection() {
        // Prevent instantiation
    }
}
