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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.github.zerkseez.reflection.types.GenericInterface;

public class GenericInterfaceReflectionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return GenericInterface.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.GenericInterface<T>";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList();
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Arrays.asList();
    }

    @Override
    public Collection<String> getExpectedPublicFields() {
        return Arrays.asList();
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList();
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public abstract java.util.List<java.util.List<T>> getListOfListOfT()",
                "public abstract java.util.List<java.util.Set<? extends T>> getListOfSetOfWildcardExtendsT()",
                "public abstract java.util.List<java.util.Set<? super T>> getListOfSetOfWildcardSuperT()",
                "public abstract java.util.List<T> getListOfT()",
                "public abstract T getT()"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList("T");
    }
}
