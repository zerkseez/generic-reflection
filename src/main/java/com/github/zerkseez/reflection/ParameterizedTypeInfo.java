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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterizedTypeInfo extends AbstractClassInfo<ParameterizedType> {
    private final ClassInfo classInfo;
    private final List<TypeVariableInfo> typeVariableSubstitutes;

    public ParameterizedTypeInfo(final ParameterizedType type) {
        this(type, null);
    }

    public ParameterizedTypeInfo(
            final ParameterizedType type,
            final List<TypeVariableInfo> typeVariableSubstitutes
    ) {
        super(type);
        this.classInfo = ClassInfo.of((Class<?>) type.getRawType());
        this.typeVariableSubstitutes = typeVariableSubstitutes;
    }

    @Override
    protected String doGetId() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ParameterizedType:");
        sb.append(getFullBinaryName());
        sb.append('<');
        sb.append(ReflectionUtils.joinStrings(", ",
                getTypeVariables().stream()
                        .map(i -> i.getId())
                        .iterator()));
        sb.append('>');
        return sb.toString();
    }

    @Override
    protected Class<?> doGetErasedClass() {
        return classInfo.getErasedClass();
    }

    @Override
    protected String doGetFullBinaryName() {
        return classInfo.getFullBinaryName();
    }

    @Override
    protected String doGetFullCanonicalName() {
        return classInfo.getFullCanonicalName();
    }

    @Override
    protected String doGetSimpleName() {
        return classInfo.getSimpleName();
    }

    @Override
    protected String doGetPackageName() {
        return classInfo.getPackageName();
    }

    @Override
    protected int doGetModifiers() {
        return classInfo.getModifiers();
    }

    @Override
    protected TypeInfo<?> doGetSuperClass() {
        if (classInfo.getSuperClass() == null) {
            return null;
        }
        return classInfo.getSuperClass().substituteTypeVariableValues(getTypeVariables());
    }

    @Override
    protected List<TypeInfo<?>> doGetInterfaces() {
        return classInfo.getInterfaces().stream()
                .map(i -> i.substituteTypeVariableValues(getTypeVariables()))
                .collect(Collectors.toList());
    }

    @Override
    protected List<FieldInfo> doGetDeclaredFields() {
        return Arrays.stream(classInfo.getType().getDeclaredFields())
                .map(i -> new FieldInfo(this, i))
                .collect(Collectors.toList());
    }
    
    @Override
    protected List<ConstructorInfo> doGetPublicConstructors() {
        return Arrays.stream(classInfo.getType().getConstructors())
                .map(i -> new ConstructorInfo(this, i))
                .collect(Collectors.toList());
    }
    
    @Override
    protected List<ConstructorInfo> doGetDeclaredConstructors() {
        return Arrays.stream(classInfo.getType().getDeclaredConstructors())
                .map(i -> new ConstructorInfo(this, i))
                .collect(Collectors.toList());
    }

    @Override
    protected List<MethodInfo> doGetDeclaredMethods() {
        return Arrays.stream(classInfo.getType().getDeclaredMethods())
                .filter(i -> !i.isBridge())
                .map(i -> new MethodInfo(this, i))
                .collect(Collectors.toList());
    }

    @Override
    protected boolean doGetIsPrimitive() {
        return classInfo.isPrimitive();
    }

    @Override
    protected boolean doGetIsArray() {
        return classInfo.isArray();
    }

    @Override
    protected TypeInfo<?> doGetArrayElementType() {
        return classInfo.getArrayElementType();
    }

    @Override
    protected boolean doGetIsEnum() {
        return classInfo.isEnum();
    }

    @Override
    protected List<Object> doGetEnumValues() {
        return classInfo.getEnumValues();
    }

    @Override
    protected boolean doGetIsInterface() {
        return classInfo.isInterface();
    }

    @Override
    protected boolean doGetIsAnnotation() {
        return classInfo.isAnnotation();
    }

    @Override
    protected boolean doGetIsAnonymousClass() {
        return classInfo.isAnonymousClass();
    }

    @Override
    protected boolean doGetIsMemberClass() {
        return classInfo.isMemberClass();
    }

    @Override
    protected boolean doGetIsLocalClass() {
        return classInfo.isLocalClass();
    }

    @Override
    protected boolean doGetIsSynthetic() {
        return classInfo.isSynthetic();
    }

    @Override
    protected boolean doGetIsTypeVariable() {
        return classInfo.isTypeVariable();
    }

    @Override
    protected String doGetTypeVariableName() {
        return classInfo.getTypeVariableName();
    }

    @Override
    protected TypeInfo<?> doGetTypeVariableValue() {
        return null;
    }

    @Override
    protected boolean doGetIsWildcardType() {
        return classInfo.isWildcardType();
    }

    @Override
    protected List<TypeInfo<?>> doGetExtendsBounds() {
        return classInfo.getExtendsBounds();
    }

    @Override
    protected List<TypeInfo<?>> doGetSuperBounds() {
        return classInfo.getSuperBounds();
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

        final Iterator<Type> iterator = Arrays.stream(getType().getActualTypeArguments()).iterator();
        return classInfo.getTypeVariables().stream()
                .map(i -> i.withValue(Reflection.getTypeInfo(iterator.next())))
                .collect(Collectors.toList());
    }

    @Override
    public TypeInfo<?> substituteTypeVariableValues(final List<TypeVariableInfo> typeVariables) {
        if (hasTypeVariables()) {
            final List<TypeVariableInfo> substitutionResults = getTypeVariables().stream()
                    .map(i -> i.withValue(i.substituteTypeVariableValues(typeVariables)))
                    .collect(Collectors.toList());
            return new ParameterizedTypeInfo(this.getType(), substitutionResults);
        }
        return this;
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        return classInfo.getAnnotatedElement();
    }
}
