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

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractClassInfo<T extends Type> extends AbstractTypeInfo<T> {
    public AbstractClassInfo(final T type) {
        super(type);
    }

    @Override
    protected List<FieldInfo> doGetPublicFields() {
        final Map<String, FieldInfo> fields = new HashMap<String, FieldInfo>();
        if (getSuperClass() != null) {
            for (FieldInfo field : getSuperClass().getPublicFields()) {
                fields.put(field.getName(), field);
            }
        }
        for (FieldInfo field : getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers())) {
                fields.put(field.getName(), field);
            }
        }
        return fields.values().stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
    }

    @Override
    protected List<MethodInfo> doGetPublicMethods() {
        final Map<String, MethodInfo> methods = new HashMap<String, MethodInfo>();
        for (TypeInfo<?> iface : getInterfaces()) {
            for (MethodInfo method : iface.getPublicMethods()) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    methods.put(method.getSignature(), method);
                }
            }
        }
        if (getSuperClass() != null) {
            for (MethodInfo method : getSuperClass().getPublicMethods()) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    methods.put(method.getSignature(), method);
                }
            }
        }
        for (MethodInfo method : getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())) {
                methods.put(method.getSignature(), method);
            }
        }
        return Collections.unmodifiableList(
                methods.values().stream()
                        .sorted((a, b) -> a.getSignature().compareTo(b.getSignature()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String toString(final ToStringContext context, final boolean includeTypeVariables) {
        final StringBuilder sb = new StringBuilder();

        if (isArray()) {
            sb.append(getArrayElementType().toString(context, includeTypeVariables)).append("[]");
        }
        else {
            final String packagePrefix = getPackageName().isEmpty() ? "" : String.format("%s.", getPackageName());
            final String canonicalNameExPackageName = getFullCanonicalName().substring(packagePrefix.length());
            final String parts[] = canonicalNameExPackageName.split("\\.");

            int i = parts.length;
            boolean imported = false;
            while (i > 0 && !imported) {
                final String n = String.format(
                        "%s%s", packagePrefix, ReflectionUtils.joinStrings(".", Arrays.copyOf(parts, i--))
                );
                if (context.isImported(n)) {
                    sb.append(ReflectionUtils.joinStrings(".", Arrays.copyOfRange(parts, i, parts.length)));
                    imported = true;
                }
            }
            if (!imported) {
                sb.append(getFullCanonicalName());
            }

            if (includeTypeVariables && hasTypeVariables()) {
                final List<TypeVariableInfo> typeVariables = getTypeVariables();
                sb.append('<');
                for (int j = 0; j < typeVariables.size(); j++) {
                    if (j != 0) {
                        sb.append(", ");
                    }
                    sb.append(typeVariables.get(j).toString(context, includeTypeVariables));
                }
                sb.append('>');
            }
        }

        return sb.toString();
    }
}
