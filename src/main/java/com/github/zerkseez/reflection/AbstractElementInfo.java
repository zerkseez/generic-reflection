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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractElementInfo implements ElementInfo {
    private final Cache<String> id;
    
    public AbstractElementInfo() {
        this.id = new Cache<String>(this, () -> this.doGetId());
    }
    
    @Override
    public final String getId() {
        return id.get();
    }

    protected abstract String doGetId();

    @Override
    public final List<Annotation> getAnnotations() {
        if (getAnnotatedElement() != null) {
            return Arrays.asList(getAnnotatedElement().getAnnotations());
        }
        return Collections.emptyList();
    }

    @Override
    public final <A extends Annotation> A getAnnotation(final Class<A> annotationType) {
        if (getAnnotatedElement() != null) {
            return getAnnotatedElement().getAnnotation(annotationType);
        }
        return null;
    }

    @Override
    public final boolean isAnnotationPresent(final Class<? extends Annotation> annotationType) {
        if (getAnnotatedElement() != null) {
            return getAnnotatedElement().isAnnotationPresent(annotationType);
        }
        return false;
    }
    
    protected abstract AnnotatedElement getAnnotatedElement();
}
