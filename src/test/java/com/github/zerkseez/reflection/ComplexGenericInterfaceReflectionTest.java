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

import com.github.zerkseez.reflection.types.ComplexGenericInterface;

public class ComplexGenericInterfaceReflectionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return ComplexGenericInterface.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.ComplexGenericInterface<X, Y, Z extends com.github.zerkseez.reflection.types.ComplexGenericInterface<Y, X, Z>>";
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
                "public abstract java.util.List<java.util.List<Z>> getListOfListOfZ()",
                "public abstract java.util.List<java.util.Set<? extends Z>> getListOfSetOfWildcardExtendsZ()",
                "public abstract java.util.List<java.util.Set<? super Z>> getListOfSetOfWildcardSuperZ()",
                "public abstract java.util.List<Z> getListOfZ()",
                "public abstract java.util.Map<X, Y> getMapOfXY()",
                "public abstract java.util.Map<Y, X> getMapOfYX()",
                "public abstract Z getZ()"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList(
                "X", "Y", "Z extends com.github.zerkseez.reflection.types.ComplexGenericInterface<Y, X, Z>"
        );
    }
}
