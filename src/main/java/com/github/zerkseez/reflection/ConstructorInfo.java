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

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.List;

public class ConstructorInfo extends AbstractExecutableInfo<Constructor<?>> {
    public ConstructorInfo(final TypeInfo<?> declaringElement, final Constructor<?> constructor) {
        super(declaringElement, constructor);
    }

    @Override
    protected String doGetId() {
        return String.format("%s|Constructor", getDeclaringElement().getId());
    }
    
    @Override
    protected String doGetName() {
        return ((TypeInfo<?>)getDeclaringElement()).getSimpleName();
    }

    @Override
    protected String doGetSignature() {
        final TypeInfo.ToStringContext context = new TypeInfo.DefaultToStringContext();
        final StringBuilder sb = new StringBuilder();
        if (hasDeclaredTypeVariables()) {
            sb.append('<');
            sb.append(ReflectionUtils.joinStrings(", ",
                    getDeclaredTypeVariables().stream()
                            .map(i -> i.toString(context, true))
                            .iterator()
            ));
            sb.append("> ");
        }
        sb.append(getName()).append('(');
        sb.append(ReflectionUtils.joinStrings(", ",
                getParameters().stream()
                        .map(i -> i.getType().toString(context, true))
                        .iterator()
        ));
        sb.append(')');
        return sb.toString();
    }

    @Override
    protected String doGetDefaultStringRepresentation() {
        final TypeInfo.ToStringContext context = new TypeInfo.DefaultToStringContext();
        for (TypeVariableInfo typeVariable : getTypeVariables()) {
            boolean isDirectlyDeclared = false;
            for (TypeVariableInfo declaredTypeVariable : getDeclaredTypeVariables()) {
                if (typeVariable.getId().equals(declaredTypeVariable.getId())) {
                    isDirectlyDeclared = true;
                    break;
                }
            }
            if (!isDirectlyDeclared) {
                context.defineTypeVariable(typeVariable.getId());
            }
        }

        final StringBuilder sb = new StringBuilder();

        sb.append(ReflectionUtils.getModifierString(getModifiers()));
        if (sb.length() > 0) {
            sb.append(' ');
        }

        if (hasDeclaredTypeVariables()) {
            sb.append('<');
            sb.append(ReflectionUtils.joinStrings(", ",
                    getDeclaredTypeVariables().stream()
                            .map(i -> i.toString(context, true))
                            .iterator()
            ));
            sb.append("> ");
        }

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

    public static class ConstructorInfoComparator implements Comparator<ConstructorInfo> {
        @Override
        public int compare(final ConstructorInfo a, final ConstructorInfo b) {
            return a.getSignature().compareTo(b.getSignature());
        }
    }
}
