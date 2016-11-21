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

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassInfo extends AbstractClassInfo<Class<?>> {
    private static final Map<Class<?>, ClassInfo> CACHE = new Hashtable<Class<?>, ClassInfo>();

    public static ClassInfo of(final Class<?> type) {
        if (type == null) {
            return null;
        }
        ClassInfo classInfo = CACHE.get(type);
        if (classInfo == null) {
            classInfo = new ClassInfo(type);
            CACHE.put(type, classInfo);
        }
        return classInfo;
    }

    private final List<TypeVariableInfo> typeVariableSubstitutes;

    protected ClassInfo(final Class<?> type) {
        this(type, null);
    }

    protected ClassInfo(final Class<?> type, final List<TypeVariableInfo> typeVariableSubstitutes) {
        super(type);
        this.typeVariableSubstitutes = typeVariableSubstitutes;
    }

    @Override
    protected String doGetId() {
        return String.format("Class:%s", getFullBinaryName());
    }

    @Override
    protected Class<?> doGetErasedClass() {
        return getType();
    }

    @Override
    protected String doGetFullBinaryName() {
        return getType().getName();
    }

    @Override
    protected String doGetFullCanonicalName() {
        return getType().getCanonicalName();
    }

    @Override
    protected String doGetSimpleName() {
        return getType().getSimpleName();
    }

    @Override
    protected String doGetPackageName() {
        return (getType().getPackage() == null) ? "" : getType().getPackage().getName();
    }

    @Override
    protected int doGetModifiers() {
        return getType().getModifiers();
    }

    @Override
    protected TypeInfo<?> doGetSuperClass() {
        return Reflection.getTypeInfo(getType().getGenericSuperclass());
    }

    @Override
    protected List<TypeInfo<?>> doGetInterfaces() {
        return Arrays.stream(getType().getGenericInterfaces())
                .map(i -> Reflection.getTypeInfo(i))
                .collect(Collectors.toList());
    }

    @Override
    protected List<FieldInfo> doGetDeclaredFields() {
        return Arrays.stream(getType().getDeclaredFields())
                .map(i -> new FieldInfo(this, i))
                .collect(Collectors.toList());
    }
    
    @Override
    protected List<ConstructorInfo> doGetPublicConstructors() {
        return Arrays.stream(getType().getConstructors())
                .map(i -> new ConstructorInfo(this, i))
                .collect(Collectors.toList());
    }
    
    @Override
    protected List<ConstructorInfo> doGetDeclaredConstructors() {
        return Arrays.stream(getType().getDeclaredConstructors())
                .map(i -> new ConstructorInfo(this, i))
                .collect(Collectors.toList());
    }

    @Override
    protected List<MethodInfo> doGetDeclaredMethods() {
        return Arrays.stream(getType().getDeclaredMethods())
                .filter(i -> !i.isBridge())
                .map(i -> new MethodInfo(this, i))
                .collect(Collectors.toList());
    }

    @Override
    protected boolean doGetIsPrimitive() {
        return getType().isPrimitive();
    }

    @Override
    protected boolean doGetIsArray() {
        return getType().isArray();
    }

    @Override
    protected TypeInfo<?> doGetArrayElementType() {
        return Reflection.getTypeInfo(getType().getComponentType());
    }

    @Override
    protected boolean doGetIsEnum() {
        return getType().isEnum();
    }

    @Override
    protected List<Object> doGetEnumValues() {
        return Arrays.asList(getType().getEnumConstants());
    }

    @Override
    protected boolean doGetIsInterface() {
        return getType().isInterface();
    }

    @Override
    protected boolean doGetIsAnnotation() {
        return getType().isAnnotation();
    }

    @Override
    protected boolean doGetIsAnonymousClass() {
        return getType().isAnonymousClass();
    }

    @Override
    protected boolean doGetIsMemberClass() {
        return getType().isMemberClass();
    }

    @Override
    protected boolean doGetIsLocalClass() {
        return getType().isLocalClass();
    }

    @Override
    protected boolean doGetIsSynthetic() {
        return getType().isSynthetic();
    }

    @Override
    protected boolean doGetIsTypeVariable() {
        return false;
    }

    @Override
    protected String doGetTypeVariableName() {
        return "";
    }

    @Override
    protected TypeInfo<?> doGetTypeVariableValue() {
        return null;
    }

    @Override
    protected boolean doGetIsWildcardType() {
        return false;
    }

    @Override
    protected List<TypeInfo<?>> doGetExtendsBounds() {
        return Collections.emptyList();
    }

    @Override
    protected List<TypeInfo<?>> doGetSuperBounds() {
        return Collections.emptyList();
    }

    @Override
    protected List<TypeVariableInfo> doGetTypeVariables() {
        return getDeclaredTypeVariables();
    }
    
    @Override
    protected List<TypeVariableInfo> doGetDeclaredTypeVariables() {
        if (typeVariableSubstitutes != null) {
            return typeVariableSubstitutes;
        }
        
        return Arrays.stream(getType().getTypeParameters())
                .map(i -> new TypeVariableInfo(i))
                .collect(Collectors.toList());
    }

    @Override
    public TypeInfo<?> substituteTypeVariableValues(final List<TypeVariableInfo> typeVariables) {
        if (hasTypeVariables()) {
            final List<TypeVariableInfo> substitutionResults = getTypeVariables().stream()
                    .map(i -> i.withValue(i.substituteTypeVariableValues(typeVariables)))
                    .collect(Collectors.toList());
            return new ClassInfo(this.getType(), substitutionResults);
        }
        return this;
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        return getType();
    }
}
