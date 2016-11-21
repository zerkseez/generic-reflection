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

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTypeInfo<T extends Type> extends AbstractElementInfo implements TypeInfo<T> {
    private final T type;
    private final Cache<Class<?>> erasedClass;
    private final Cache<String> fullBinaryName;
    private final Cache<String> fullCanonicalName;
    private final Cache<String> simpleName;
    private final Cache<String> packageName;
    private final Cache<Integer> modifiers;
    private final Cache<TypeInfo<?>> superClass;
    private final Cache<List<TypeInfo<?>>> interfaces;
    private final Cache<List<FieldInfo>> publicFields;
    private final Cache<List<FieldInfo>> declaredFields;
    private final Cache<List<ConstructorInfo>> publicConstructors;
    private final Cache<List<ConstructorInfo>> declaredConstructors;
    private final Cache<List<MethodInfo>> publicMethods;
    private final Cache<List<MethodInfo>> declaredMethods;
    private final Cache<Boolean> isPrimitive;
    private final Cache<Boolean> isArray;
    private final Cache<TypeInfo<?>> arrayElementType;
    private final Cache<Boolean> isEnum;
    private final Cache<List<Object>> enumValues;
    private final Cache<Boolean> isInterface;
    private final Cache<Boolean> isAnnotation;
    private final Cache<Boolean> isAnonymousClass;
    private final Cache<Boolean> isMemberClass;
    private final Cache<Boolean> isLocalClass;
    private final Cache<Boolean> isSynthetic;
    private final Cache<Boolean> isTypeVariable;
    private final Cache<String> typeVariableName;
    private final Cache<TypeInfo<?>> typeVariableValue;
    private final Cache<Boolean> isWildcardType;
    private final Cache<List<TypeInfo<?>>> extendsBounds;
    private final Cache<List<TypeInfo<?>>> superBounds;
    private final Cache<List<TypeVariableInfo>> typeVariables;
    private final Cache<List<TypeVariableInfo>> declaredTypeVariables;
    private final Cache<String> defaultStringRepresentation;

    public AbstractTypeInfo(final T type) {
        this.type = type;
        this.erasedClass = new Cache<Class<?>>(this, () -> this.doGetErasedClass());
        this.fullBinaryName = new Cache<String>(this, () -> this.doGetFullBinaryName());
        this.fullCanonicalName = new Cache<String>(this, () -> this.doGetFullCanonicalName());
        this.simpleName = new Cache<String>(this, () -> this.doGetSimpleName());
        this.packageName = new Cache<String>(this, () -> this.doGetPackageName());
        this.modifiers = new Cache<Integer>(this, () -> this.doGetModifiers());
        this.superClass = new Cache<TypeInfo<?>>(this, () -> this.doGetSuperClass());
        this.interfaces = new Cache<List<TypeInfo<?>>>(this, () -> this.doGetInterfaces());
        this.publicFields = new Cache<List<FieldInfo>>(this, () -> this.doGetPublicFields());
        this.declaredFields = new Cache<List<FieldInfo>>(this, () -> this.doGetDeclaredFields());
        this.publicConstructors = new Cache<List<ConstructorInfo>>(this, () -> this.doGetPublicConstructors());
        this.declaredConstructors = new Cache<List<ConstructorInfo>>(this, () -> this.doGetDeclaredConstructors());
        this.publicMethods = new Cache<List<MethodInfo>>(this, () -> this.doGetPublicMethods());
        this.declaredMethods = new Cache<List<MethodInfo>>(this, () -> this.doGetDeclaredMethods());
        this.isPrimitive = new Cache<Boolean>(this, () -> this.doGetIsPrimitive());
        this.isArray = new Cache<Boolean>(this, () -> this.doGetIsArray());
        this.arrayElementType = new Cache<TypeInfo<?>>(this, () -> this.doGetArrayElementType());
        this.isEnum = new Cache<Boolean>(this, () -> this.doGetIsEnum());
        this.enumValues = new Cache<List<Object>>(this, () -> this.doGetEnumValues());
        this.isInterface = new Cache<Boolean>(this, () -> this.doGetIsInterface());
        this.isAnnotation = new Cache<Boolean>(this, () -> this.doGetIsAnnotation());
        this.isAnonymousClass = new Cache<Boolean>(this, () -> this.doGetIsAnonymousClass());
        this.isMemberClass = new Cache<Boolean>(this, () -> this.doGetIsMemberClass());
        this.isLocalClass = new Cache<Boolean>(this, () -> this.doGetIsLocalClass());
        this.isSynthetic = new Cache<Boolean>(this, () -> this.doGetIsSynthetic());
        this.isTypeVariable = new Cache<Boolean>(this, () -> this.doGetIsTypeVariable());
        this.typeVariableName = new Cache<String>(this, () -> this.doGetTypeVariableName());
        this.typeVariableValue = new Cache<TypeInfo<?>>(this, () -> this.doGetTypeVariableValue());
        this.isWildcardType = new Cache<Boolean>(this, () -> this.doGetIsWildcardType());
        this.extendsBounds = new Cache<List<TypeInfo<?>>>(this, () -> this.doGetExtendsBounds());
        this.superBounds = new Cache<List<TypeInfo<?>>>(this, () -> this.doGetSuperBounds());
        this.typeVariables = new Cache<List<TypeVariableInfo>>(this, () -> this.doGetTypeVariables());
        this.declaredTypeVariables = new Cache<List<TypeVariableInfo>>(this, () -> this.doGetDeclaredTypeVariables());
        this.defaultStringRepresentation = new Cache<String>(this, () -> this.doGetDefaultStringRepresentation());
    }

    @Override
    public final T getType() {
        return type;
    }

    @Override
    public final Class<?> getErasedClass() {
        return erasedClass.get();
    }

    protected abstract Class<?> doGetErasedClass();

    @Override
    public final String getFullBinaryName() {
        return fullBinaryName.get();
    }

    protected abstract String doGetFullBinaryName();

    @Override
    public final String getFullCanonicalName() {
        return fullCanonicalName.get();
    }

    protected abstract String doGetFullCanonicalName();

    @Override
    public final String getSimpleName() {
        return simpleName.get();
    }

    protected abstract String doGetSimpleName();

    @Override
    public final String getPackageName() {
        return packageName.get();
    }

    protected abstract String doGetPackageName();

    @Override
    public final int getModifiers() {
        return modifiers.get();
    }

    protected abstract int doGetModifiers();

    @Override
    public final TypeInfo<?> getSuperClass() {
        return superClass.get();
    }

    protected abstract TypeInfo<?> doGetSuperClass();

    @Override
    public final List<TypeInfo<?>> getInterfaces() {
        return Collections.unmodifiableList(interfaces.get());
    }

    protected abstract List<TypeInfo<?>> doGetInterfaces();

    @Override
    public final List<FieldInfo> getPublicFields() {
        return Collections.unmodifiableList(publicFields.get());
    }

    protected abstract List<FieldInfo> doGetPublicFields();

    @Override
    public FieldInfo getPublicField(final String name) {
        for (FieldInfo fieldInfo : getPublicFields()) {
            if (fieldInfo.getName().equals(name)) {
                return fieldInfo;
            }
        }
        return null;
    }

    @Override
    public final List<FieldInfo> getDeclaredFields() {
        return Collections.unmodifiableList(declaredFields.get());
    }

    protected abstract List<FieldInfo> doGetDeclaredFields();
    
    @Override
    public final List<ConstructorInfo> getPublicConstructors() {
        return Collections.unmodifiableList(publicConstructors.get());
    }

    protected abstract List<ConstructorInfo> doGetPublicConstructors();
    
    @Override
    public final List<ConstructorInfo> getDeclaredConstructors() {
        return Collections.unmodifiableList(declaredConstructors.get());
    }

    protected abstract List<ConstructorInfo> doGetDeclaredConstructors();

    @Override
    public final List<MethodInfo> getPublicMethods() {
        return Collections.unmodifiableList(publicMethods.get());
    }

    protected abstract List<MethodInfo> doGetPublicMethods();

    @Override
    public final List<MethodInfo> getDeclaredMethods() {
        return Collections.unmodifiableList(declaredMethods.get());
    }

    protected abstract List<MethodInfo> doGetDeclaredMethods();

    @Override
    public final boolean isPrimitive() {
        return isPrimitive.get();
    }

    protected abstract boolean doGetIsPrimitive();

    @Override
    public final boolean isArray() {
        return isArray.get();
    }

    protected abstract boolean doGetIsArray();

    @Override
    public final TypeInfo<?> getArrayElementType() {
        return arrayElementType.get();
    }

    protected abstract TypeInfo<?> doGetArrayElementType();

    @Override
    public final boolean isEnum() {
        return isEnum.get();
    }

    protected abstract boolean doGetIsEnum();

    @Override
    public final List<Object> getEnumValues() {
        return Collections.unmodifiableList(enumValues.get());
    }

    protected abstract List<Object> doGetEnumValues();

    @Override
    public final boolean isInterface() {
        return isInterface.get();
    }

    protected abstract boolean doGetIsInterface();

    @Override
    public final boolean isAnnotation() {
        return isAnnotation.get();
    }

    protected abstract boolean doGetIsAnnotation();

    @Override
    public final boolean isAnonymousClass() {
        return isAnonymousClass.get();
    }

    protected abstract boolean doGetIsAnonymousClass();

    @Override
    public final boolean isMemberClass() {
        return isMemberClass.get();
    }

    protected abstract boolean doGetIsMemberClass();

    @Override
    public final boolean isLocalClass() {
        return isLocalClass.get();
    }

    protected abstract boolean doGetIsLocalClass();

    @Override
    public final boolean isSynthetic() {
        return isSynthetic.get();
    }

    protected abstract boolean doGetIsSynthetic();

    @Override
    public final boolean isTypeVariable() {
        return isTypeVariable.get();
    }

    protected abstract boolean doGetIsTypeVariable();

    @Override
    public final String getTypeVariableName() {
        return typeVariableName.get();
    }

    protected abstract String doGetTypeVariableName();

    @Override
    public final TypeInfo<?> getTypeVariableValue() {
        return typeVariableValue.get();
    }

    protected abstract TypeInfo<?> doGetTypeVariableValue();

    @Override
    public final boolean isWildcardType() {
        return isWildcardType.get();
    }

    protected abstract boolean doGetIsWildcardType();

    @Override
    public final List<TypeInfo<?>> getExtendsBounds() {
        return Collections.unmodifiableList(extendsBounds.get());
    }

    protected abstract List<TypeInfo<?>> doGetExtendsBounds();

    @Override
    public final List<TypeInfo<?>> getSuperBounds() {
        return Collections.unmodifiableList(superBounds.get());
    }

    protected abstract List<TypeInfo<?>> doGetSuperBounds();

    @Override
    public final boolean hasTypeVariables() {
        return !getTypeVariables().isEmpty();
    }

    @Override
    public final List<TypeVariableInfo> getTypeVariables() {
        return Collections.unmodifiableList(typeVariables.get());
    }

    protected abstract List<TypeVariableInfo> doGetTypeVariables();
    
    @Override
    public final boolean hasDeclaredTypeVariables() {
        return !getDeclaredTypeVariables().isEmpty();
    }

    @Override
    public final List<TypeVariableInfo> getDeclaredTypeVariables() {
        return Collections.unmodifiableList(declaredTypeVariables.get());
    }

    protected abstract List<TypeVariableInfo> doGetDeclaredTypeVariables();

    @Override
    public final String toString() {
        return defaultStringRepresentation.get();
    }

    protected String doGetDefaultStringRepresentation() {
        return toString(new ToStringContext() {
            private final Set<String> definedTypeVariables = new HashSet<String>();

            @Override
            public boolean isImported(final String canonicalClassName) {
                return false;
            }

            @Override
            public boolean isTypeVariableDefined(final String typeVariableName) {
                if (definedTypeVariables.contains(typeVariableName)) {
                    return true;
                }
                return false;
            }

            @Override
            public void defineTypeVariable(final String typeVariableName) {
                definedTypeVariables.add(typeVariableName);
            }
        }, true);
    }

    protected String renderSuperAndExtendsBounds(final ToStringContext context, final boolean includeTypeVariables) {
        final StringBuilder sb = new StringBuilder();
        if (!getSuperBounds().isEmpty()) {
            sb.append(" super ");
            final List<TypeInfo<?>> superBounds = getSuperBounds();
            for (int i = 0; i < superBounds.size(); i++) {
                if (i != 0) {
                    sb.append(" & ");
                }
                sb.append(superBounds.get(i).toString(context, includeTypeVariables));
            }
        }
        if (!getExtendsBounds().isEmpty()) {
            sb.append(" extends ");
            final List<TypeInfo<?>> extendsBounds = getExtendsBounds();
            for (int i = 0; i < extendsBounds.size(); i++) {
                if (i != 0) {
                    sb.append(" & ");
                }
                sb.append(extendsBounds.get(i).toString(context, includeTypeVariables));
            }
        }
        return sb.toString();
    }
}
