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
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MethodInfo extends AbstractMemberInfo {
    private final Method method;

    public MethodInfo(final ElementInfo declaringElement, final Method method) {
        super(declaringElement);
        this.method = method;
    }

    @Override
    protected String doGetId() {
        return String.format("%s|Method:%s", getDeclaringElement().getId(), getName());
    }

    public Method getMethod() {
        return method;
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        return getMethod();
    }

    public int getModifiers() {
        return getMethod().getModifiers();
    }

    public TypeInfo<?> getReturnType() {
        return resolveActualType(Reflection.getTypeInfo(getMethod().getGenericReturnType()));
    }

    @Override
    public String getName() {
        return getMethod().getName();
    }

    public List<ParameterInfo> getParameters() {
        return Arrays.stream(getMethod().getParameters())
                .map(i -> new ParameterInfo(
                        i, resolveActualType(Reflection.getTypeInfo(i.getParameterizedType()))
                ))
                .collect(Collectors.toList());
    }

    public List<TypeInfo<?>> getExceptionTypes() {
        return Arrays.stream(getMethod().getGenericExceptionTypes())
                .map(i -> Reflection.getTypeInfo(i))
                .collect(Collectors.toList());
    }

    public String getSignature() {
        final TypeInfo.ToStringContext context = new TypeInfo.DefaultToStringContext();
        final StringBuilder sb = new StringBuilder();
        if (getMethod().getTypeParameters().length > 0) {
            sb.append('<');
            sb.append(ReflectionUtils.joinStrings(", ",
                    Arrays.stream(getMethod().getTypeParameters())
                            .map(i -> Reflection.getTypeInfo(i).toString(context, true))
                            .iterator()
            ));
            sb.append("> ");
        }
        sb.append(getReturnType().toString()).append(' ').append(getName()).append('(');
        sb.append(ReflectionUtils.joinStrings(", ",
                getParameters().stream()
                        .map(i -> i.getType().toString(context, true))
                        .iterator()
        ));
        sb.append(')');
        return sb.toString();
    }

    @Override
    public List<TypeVariableInfo> getTypeVariables() {
        final List<TypeVariableInfo> typeVariables = new ArrayList<TypeVariableInfo>(super.getTypeVariables());
        typeVariables.addAll(
                Arrays.stream(getMethod().getTypeParameters())
                        .map(i -> new TypeVariableInfo(i))
                        .collect(Collectors.toList())
        );
        return Collections.unmodifiableList(typeVariables);
    }

    @Override
    public String toString() {
        final TypeInfo.ToStringContext context = new TypeInfo.DefaultToStringContext();
        if (getDeclaringElement() instanceof TypeInfo) {
            ((TypeInfo<?>) getDeclaringElement()).toString(context, true);
        }

        final StringBuilder sb = new StringBuilder();

        sb.append(ReflectionUtils.getModifierString(getModifiers()));
        if (sb.length() > 0) {
            sb.append(' ');
        }

        if (getMethod().getTypeParameters().length > 0) {
            sb.append('<');
            sb.append(ReflectionUtils.joinStrings(", ",
                    Arrays.stream(getMethod().getTypeParameters())
                            .map(i -> new TypeVariableInfo(i).toString(context, true))
                            .iterator()
            ));
            sb.append("> ");
        }

        sb.append(getReturnType().toString(context, true));
        sb.append(' ');

        sb.append(getName());

        sb.append('(');
        final List<ParameterInfo> parameters = getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            final ParameterInfo parameter = parameters.get(i);
            sb.append(parameter.getType().toString(context, true));
            sb.append(' ');
            sb.append(parameter.getName());
        }
        sb.append(')');

        final List<TypeInfo<?>> exceptionTypes = getExceptionTypes();
        if (!exceptionTypes.isEmpty()) {
            sb.append(" throws ");
            sb.append(ReflectionUtils.joinStrings(", ",
                    exceptionTypes.stream()
                            .map(i -> i.toString(context, true))
                            .iterator()
            ));
        }
        return sb.toString();
    }

    public static class MethodInfoComparator implements Comparator<MethodInfo> {
        @Override
        public int compare(final MethodInfo a, final MethodInfo b) {
            if (Modifier.isStatic(a.getModifiers()) && !Modifier.isStatic(b.getModifiers())) {
                return -1;
            }
            if (!Modifier.isStatic(a.getModifiers()) && Modifier.isStatic(b.getModifiers())) {
                return 1;
            }
            final int nameComparisonResult = a.getName().compareTo(b.getName());
            if (nameComparisonResult != 0) {
                return nameComparisonResult;
            }
            return a.getSignature().compareTo(b.getSignature());
        }
    }
}
