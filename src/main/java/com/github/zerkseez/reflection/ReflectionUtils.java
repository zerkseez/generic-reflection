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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class ReflectionUtils {
    public static String joinStrings(final String separator, final String[] parts) {
        return joinStrings(separator, Arrays.stream(parts).iterator());
    }

    public static String joinStrings(final String separator, final Iterable<String> parts) {
        return joinStrings(separator, parts.iterator());
    }

    public static String joinStrings(final String separator, final Iterator<String> parts) {
        final StringBuilder sb = new StringBuilder();
        if (parts.hasNext()) {
            sb.append(parts.next());
        }
        while (parts.hasNext()) {
            sb.append(separator).append(parts.next());
        }
        return sb.toString();
    }

    public static String getModifierString(final int modifiers) {
        final List<String> parts = new ArrayList<String>();
        if (Modifier.isPublic(modifiers)) {
            parts.add("public");
        }
        if (Modifier.isProtected(modifiers)) {
            parts.add("protected");
        }
        if (Modifier.isPrivate(modifiers)) {
            parts.add("private");
        }
        if (Modifier.isAbstract(modifiers)) {
            parts.add("abstract");
        }
        if (Modifier.isStatic(modifiers)) {
            parts.add("static");
        }
        if (Modifier.isFinal(modifiers)) {
            parts.add("final");
        }
        if (Modifier.isTransient(modifiers)) {
            parts.add("transient");
        }
        if (Modifier.isVolatile(modifiers)) {
            parts.add("volatile");
        }
        if (Modifier.isSynchronized(modifiers)) {
            parts.add("synchronized");
        }
        if (Modifier.isNative(modifiers)) {
            parts.add("native");
        }
        if (Modifier.isStrict(modifiers)) {
            parts.add("strictfp");
        }
        return joinStrings(" ", parts);
    }

    private ReflectionUtils() {
        // Prevent initantiation
    }
}
