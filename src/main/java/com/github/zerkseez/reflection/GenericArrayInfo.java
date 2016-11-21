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
import java.lang.reflect.GenericArrayType;
import java.util.Collections;
import java.util.List;

public class GenericArrayInfo extends AbstractTypeInfo<GenericArrayType> {
    private final TypeInfo<?> elementTypeSubstitute;

    public GenericArrayInfo(final GenericArrayType type) {
        this(type, null);
    }

    public GenericArrayInfo(final GenericArrayType type, final TypeInfo<?> elementTypeSubstitute) {
        super(type);
        this.elementTypeSubstitute = elementTypeSubstitute;
    }

    @Override
    protected String doGetId() {
        return String.format("%s[]", getArrayElementType().getId());
    }

    @Override
    protected Class<?> doGetErasedClass() {
        return null;
    }

    @Override
    protected String doGetFullBinaryName() {
        return "";
    }

    @Override
    protected String doGetFullCanonicalName() {
        return String.format("%s[]", getArrayElementType().getFullCanonicalName());
    }

    @Override
    protected String doGetSimpleName() {
        return String.format("%s[]", getArrayElementType().getSimpleName());
    }

    @Override
    protected String doGetPackageName() {
        return "";
    }

    @Override
    protected int doGetModifiers() {
        return 0;
    }

    @Override
    protected TypeInfo<?> doGetSuperClass() {
        return null;
    }

    @Override
    protected List<TypeInfo<?>> doGetInterfaces() {
        return null;
    }

    @Override
    protected List<FieldInfo> doGetPublicFields() {
        return Collections.emptyList();
    }

    @Override
    protected List<FieldInfo> doGetDeclaredFields() {
        return Collections.emptyList();
    }
    
    @Override
    protected List<ConstructorInfo> doGetPublicConstructors() {
        return Collections.emptyList();
    }
    
    @Override
    protected List<ConstructorInfo> doGetDeclaredConstructors() {
        return Collections.emptyList();
    }

    @Override
    protected List<MethodInfo> doGetPublicMethods() {
        return ClassInfo.of(Object.class).getPublicMethods();
    }

    @Override
    protected List<MethodInfo> doGetDeclaredMethods() {
        return ClassInfo.of(Object.class).getDeclaredMethods();
    }

    @Override
    protected boolean doGetIsPrimitive() {
        return false;
    }

    @Override
    protected boolean doGetIsArray() {
        return true;
    }

    @Override
    protected TypeInfo<?> doGetArrayElementType() {
        if (elementTypeSubstitute != null) {
            return elementTypeSubstitute;
        }
        return Reflection.getTypeInfo(getType().getGenericComponentType());
    }

    @Override
    protected boolean doGetIsEnum() {
        return false;
    }

    @Override
    protected List<Object> doGetEnumValues() {
        return Collections.emptyList();
    }

    @Override
    protected boolean doGetIsInterface() {
        return false;
    }

    @Override
    protected boolean doGetIsAnnotation() {
        return false;
    }

    @Override
    protected boolean doGetIsAnonymousClass() {
        return false;
    }

    @Override
    protected boolean doGetIsMemberClass() {
        return false;
    }

    @Override
    protected boolean doGetIsLocalClass() {
        return false;
    }

    @Override
    protected boolean doGetIsSynthetic() {
        return false;
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
        return Collections.emptyList();
    }

    @Override
    public String toString(final ToStringContext context, final boolean includeTypeVariables) {
        final StringBuilder sb = new StringBuilder();
        sb.append(getArrayElementType().toString(context, includeTypeVariables)).append("[]");
        return sb.toString();
    }

    @Override
    public TypeInfo<?> substituteTypeVariableValues(final List<TypeVariableInfo> typeVariables) {
        return new GenericArrayInfo(this.getType(), getArrayElementType().substituteTypeVariableValues(typeVariables));
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        if (getType() instanceof AnnotatedElement) {
            return (AnnotatedElement) getType();
        }
        return null;
    }
}
