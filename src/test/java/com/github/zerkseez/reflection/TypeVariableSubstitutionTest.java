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
import java.util.stream.Collectors;

import com.github.zerkseez.reflection.types.GenericInterfaceWithBounds;
import com.github.zerkseez.reflection.types.SubClass;

public class TypeVariableSubstitutionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return GenericInterfaceWithBounds.class;
    }
    
    @Override
    public TypeInfo<?> getTypeInfo() {
        return super.getTypeInfo().substituteTypeVariableValues(
                super.getTypeInfo().getTypeVariables().stream()
                        .map(i -> i.withValue(Reflection.getTypeInfo(SubClass.class)))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.GenericInterfaceWithBounds<com.github.zerkseez.reflection.types.SubClass>";
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
                "public abstract java.util.List<java.util.List<com.github.zerkseez.reflection.types.SubClass>> getListOfListOfT()",
                "public abstract java.util.List<java.util.Set<? extends com.github.zerkseez.reflection.types.SubClass>> getListOfSetOfWildcardExtendsT()",
                "public abstract java.util.List<java.util.Set<? super com.github.zerkseez.reflection.types.SubClass>> getListOfSetOfWildcardSuperT()",
                "public abstract java.util.List<com.github.zerkseez.reflection.types.SubClass> getListOfT()",
                "public abstract com.github.zerkseez.reflection.types.SubClass getT()"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList("com.github.zerkseez.reflection.types.SubClass");
    }
}
