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
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TypeVariableInfo extends AbstractTypeInfo<TypeVariable<?>> {
    private final TypeInfo<?> value;

    public TypeVariableInfo(final TypeVariable<?> type) {
        this(type, null);
    }

    private TypeVariableInfo(final TypeVariable<?> type, final TypeInfo<?> value) {
        super(type);
        this.value = value;
    }

    public TypeVariableInfo withValue(final TypeInfo<?> value) {
        if (this == value) {
            return this;
        }
        return new TypeVariableInfo(getType(), value);
    }

    protected ElementInfo getDeclaringElement() {
        final Object declaringObject = getType().getGenericDeclaration();
        if (declaringObject instanceof Type) {
            return Reflection.getTypeInfo((Type) declaringObject);
        }
        else if (declaringObject instanceof Method) {
            final Method method = (Method) declaringObject;
            return new MethodInfo(Reflection.getTypeInfo(method.getDeclaringClass()), method);
        }
        throw new ReflectionException(String.format(
                "%s is not supported as declaring element", declaringObject.getClass()
        ));
    }

    @Override
    protected String doGetId() {
        return String.format("%s|TypeVariable:%s", getDeclaringElement().getId(), getTypeVariableName());
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
        return "";
    }

    @Override
    protected String doGetSimpleName() {
        return "";
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
        return false;
    }

    @Override
    protected TypeInfo<?> doGetArrayElementType() {
        return null;
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
        return true;
    }

    @Override
    protected String doGetTypeVariableName() {
        return getType().getName();
    }

    @Override
    protected boolean doGetIsWildcardType() {
        return false;
    }

    @Override
    protected List<TypeInfo<?>> doGetExtendsBounds() {
        return Arrays.stream(getType().getBounds())
                .filter(i -> !Object.class.equals(i))
                .map(i -> Reflection.getTypeInfo(i))
                .collect(Collectors.toList());
    }

    @Override
    protected List<TypeInfo<?>> doGetSuperBounds() {
        return Collections.emptyList();
    }

    @Override
    protected boolean doGetHasTypeVariables() {
        return false;
    }

    @Override
    protected List<TypeVariableInfo> doGetTypeVariables() {
        return Collections.emptyList();
    }

    @Override
    protected TypeInfo<?> doGetTypeVariableValue() {
        return value;
    }

    @Override
    public String toString(final ToStringContext context, final boolean includeTypeVariables) {
        if (getTypeVariableValue() == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(getTypeVariableName());
            if (!context.isTypeVariableDefined(getTypeVariableName())) {
                context.defineTypeVariable(getTypeVariableName());
                sb.append(renderSuperAndExtendsBounds(context, includeTypeVariables));
            }
            return sb.toString();
        }
        return getTypeVariableValue().toString(context, includeTypeVariables);
    }

    @Override
    public TypeInfo<?> substituteTypeVariableValues(final List<TypeVariableInfo> typeVariables) {
        for (TypeVariableInfo typeVariable : typeVariables) {
            if (typeVariable.getId().equals(getId())) {
                return typeVariable.getTypeVariableValue();
            }
        }
        if (getTypeVariableValue() == null) {
            return this;
        }
        return getTypeVariableValue().substituteTypeVariableValues(typeVariables);
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        return getType();
    }
}
