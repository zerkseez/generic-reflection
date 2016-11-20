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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractReflectionTest {
    public abstract Class<?> getType();
    public abstract String getExpectedStringRepresentation();
    public abstract List<String> getExpectedSuperClasses();
    public abstract Collection<String> getExpectedInterfaces();
    public abstract Collection<String> getExpectedPublicFields();
    public abstract Collection<String> getExpectedPublicConstructors();
    public abstract Collection<String> getExpectedPublicMethods();
    public abstract Collection<String> getExpectedTypeVariables();

    private TypeInfo<?> typeInfo = null;

    public TypeInfo<?> getTypeInfo() {
        return typeInfo;
    }

    @Before
    public void setup() {
        typeInfo = Reflection.getTypeInfo(getType());
    }

    @Test
    public void testToString() {
        Assert.assertEquals(getExpectedStringRepresentation(), getTypeInfo().toString());
    }

    @Test
    public void testGetSuperClass() {
        final List<String> superClasses = new ArrayList<String>();

        TypeInfo<?> t = getTypeInfo();
        while (t.getSuperClass() != null) {
            superClasses.add(t.getSuperClass().toString());
            t = t.getSuperClass();
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("Reflected superclasses:\n\"");
        sb.append(ReflectionUtils.joinStrings("\",\n\"", superClasses));
        sb.append("\"\n");
        Assert.assertEquals(
                sb.toString(),
                getExpectedSuperClasses(),
                superClasses
        );
    }

    @Test
    public void testGetInterfaces() {
        final List<String> interfaces = getTypeInfo().getInterfaces().stream()
                .map(i -> i.toString()).sorted().collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder();
        sb.append("Reflected interfaces:\n\"");
        sb.append(ReflectionUtils.joinStrings("\",\n\"", interfaces));
        sb.append("\"\n");
        Assert.assertEquals(
                sb.toString(),
                new HashSet<String>(getExpectedInterfaces()),
                new HashSet<String>(interfaces)
        );
    }

    @Test
    public void testGetPublicFields() {
        final List<String> publicFields = getTypeInfo().getPublicFields().stream()
                .sorted(new FieldInfo.FieldInfoComparator())
                .map(i -> i.toString())
                .collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder();
        sb.append("Reflected field list:\n\"");
        sb.append(ReflectionUtils.joinStrings("\",\n\"", publicFields));
        sb.append("\"\n");
        Assert.assertEquals(
                sb.toString(),
                new HashSet<String>(getExpectedPublicFields()),
                new HashSet<String>(publicFields)
        );
    }
    
    @Test
    public void testGetPublicConstructors() {
        final List<String> publicConstructors = getTypeInfo().getPublicConstructors().stream()
                .sorted(new ConstructorInfo.ConstructorInfoComparator())
                .map(i -> i.toString())
                .collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder();
        sb.append("Reflected constructor list:\n\"");
        sb.append(ReflectionUtils.joinStrings("\",\n\"", publicConstructors));
        sb.append("\"\n");
        Assert.assertEquals(
                sb.toString(),
                new HashSet<String>(getExpectedPublicConstructors()),
                new HashSet<String>(publicConstructors)
        );
    }

    @Test
    public void testGetPublicMethods() {
        final List<String> publicMethods = getTypeInfo().getPublicMethods().stream()
                .sorted(new MethodInfo.MethodInfoComparator())
                .map(i -> i.toString())
                .collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder();
        sb.append("Reflected method list:\n\"");
        sb.append(ReflectionUtils.joinStrings("\",\n\"", publicMethods));
        sb.append("\"\n");
        Assert.assertEquals(
                sb.toString(),
                new HashSet<String>(getExpectedPublicMethods()),
                new HashSet<String>(publicMethods)
        );
    }

    @Test
    public void testGetTypeVariables() {
        final List<String> typeVariables = getTypeInfo().getTypeVariables().stream()
                .map(i -> i.toString()).collect(Collectors.toList());
        final StringBuilder sb = new StringBuilder();
        sb.append("Reflected type variables:\n\"");
        sb.append(ReflectionUtils.joinStrings("\",\n\"", typeVariables));
        sb.append("\"\n");
        Assert.assertEquals(
                sb.toString(),
                new HashSet<String>(getExpectedTypeVariables()),
                new HashSet<String>(typeVariables)
        );
    }
}
