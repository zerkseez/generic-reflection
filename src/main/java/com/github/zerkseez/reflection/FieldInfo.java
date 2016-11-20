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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;

public class FieldInfo extends AbstractMemberInfo {
    private final Field field;

    public FieldInfo(final TypeInfo<?> declaringType, final Field field) {
        super(declaringType);
        this.field = field;
    }

    @Override
    protected String doGetId() {
        return String.format("%s|Field:%s", getDeclaringElement().getId(), getName());
    }

    public Field getField() {
        return field;
    }

    @Override
    protected AnnotatedElement getAnnotatedElement() {
        return getField();
    }

    @Override
    public String getName() {
        return getField().getName();
    }

    public TypeInfo<?> getType() {
        return resolveActualType(Reflection.getTypeInfo(getField().getGenericType()));
    }

    public int getModifiers() {
        return field.getModifiers();
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
        sb.append(getType().toString(context, true));
        sb.append(' ');
        sb.append(getName());
        return sb.toString();
    }

    public static class FieldInfoComparator implements Comparator<FieldInfo> {
        @Override
        public int compare(final FieldInfo a, final FieldInfo b) {
            if (Modifier.isStatic(a.getModifiers()) && !Modifier.isStatic(b.getModifiers())) {
                return -1;
            }
            if (!Modifier.isStatic(a.getModifiers()) && Modifier.isStatic(b.getModifiers())) {
                return 1;
            }
            return a.getName().compareTo(b.getName());
        }
    }
}
