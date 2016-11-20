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
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WildcardTypeInfo extends AbstractTypeInfo<WildcardType> {
    private final List<TypeInfo<?>> extendsBoundsSubtitutes;
    private final List<TypeInfo<?>> superBoundsSubtitutes;

    public WildcardTypeInfo(final WildcardType type) {
        this(type, null, null);
    }

    public WildcardTypeInfo(
            final WildcardType type,
            final List<TypeInfo<?>> extendsBoundsSubtitutes,
            final List<TypeInfo<?>> superBoundsSubtitutes
    ) {
        super(type);
        this.extendsBoundsSubtitutes = extendsBoundsSubtitutes;
        this.superBoundsSubtitutes = superBoundsSubtitutes;
    }

    @Override
    protected String doGetId() {
        final StringBuilder sb = new StringBuilder();
        sb.append("WildcardType:?");
        if (!getSuperBounds().isEmpty()) {
            sb.append(" super ");
            final List<TypeInfo<?>> superBounds = getSuperBounds();
            for (int i = 0; i < superBounds.size(); i++) {
                if (i != 0) {
                    sb.append(" & ");
                }
                sb.append(superBounds.get(i).getId());
            }
        }
        if (!getExtendsBounds().isEmpty()) {
            sb.append(" extends ");
            final List<TypeInfo<?>> extendsBounds = getExtendsBounds();
            for (int i = 0; i < extendsBounds.size(); i++) {
                if (i != 0) {
                    sb.append(" & ");
                }
                sb.append(extendsBounds.get(i).getId());
            }
        }
        return sb.toString();
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
        return false;
    }

    @Override
    protected boolean doGetIsWildcardType() {
        return true;
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
    protected List<TypeInfo<?>> doGetExtendsBounds() {
        if (extendsBoundsSubtitutes != null) {
            return extendsBoundsSubtitutes;
        }

        return Arrays.stream(getType().getUpperBounds())
                .filter(i -> !Object.class.equals(i))
                .map(i -> Reflection.getTypeInfo(i))
                .collect(Collectors.toList());
    }

    @Override
    protected List<TypeInfo<?>> doGetSuperBounds() {
        if (superBoundsSubtitutes != null) {
            return superBoundsSubtitutes;
        }

        return Arrays.stream(getType().getLowerBounds())
                .filter(i -> !Object.class.equals(i))
                .map(i -> Reflection.getTypeInfo(i))
                .collect(Collectors.toList());
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
    public String toString(final ToStringContext context, final boolean includeTypeVariables) {
        final StringBuilder sb = new StringBuilder();
        sb.append('?').append(renderSuperAndExtendsBounds(context, includeTypeVariables));
        return sb.toString();
    }

    @Override
    public TypeInfo<?> substituteTypeVariableValues(final List<TypeVariableInfo> typeVariables) {
        return new WildcardTypeInfo(
                this.getType(),
                getExtendsBounds().stream()
                        .map(i -> i.substituteTypeVariableValues(typeVariables))
                        .collect(Collectors.toList()),
                getSuperBounds().stream()
                        .map(i -> i.substituteTypeVariableValues(typeVariables))
                        .collect(Collectors.toList()));
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        if (getType() instanceof AnnotatedElement) {
            return (AnnotatedElement) getType();
        }
        return null;
    }
}
