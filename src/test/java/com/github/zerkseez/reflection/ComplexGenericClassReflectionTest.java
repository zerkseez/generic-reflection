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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.github.zerkseez.reflection.types.ComplexGenericClass;

public class ComplexGenericClassReflectionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return ComplexGenericClass.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.ComplexGenericClass<A, B, C extends com.github.zerkseez.reflection.types.ComplexGenericClass<B, A, C>>";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(Object.class.getName());
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.ComplexGenericInterface<A, B, C extends com.github.zerkseez.reflection.types.ComplexGenericClass<B, A, C>>"
        );
    }

    @Override
    public Collection<String> getExpectedPublicFields() {
        return Arrays.asList();
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList(
                "public ComplexGenericClass()"
        );
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public java.util.List<java.util.List<C>> getListOfListOfZ()",
                "public java.util.List<java.util.Set<? extends C>> getListOfSetOfWildcardExtendsZ()",
                "public java.util.List<java.util.Set<? super C>> getListOfSetOfWildcardSuperZ()",
                "public java.util.List<C> getListOfZ()",
                "public java.util.Map<A, B> getMapOfXY()",
                "public java.util.Map<B, A> getMapOfYX()",
                "public C getZ()",
                "public native int hashCode()",
                "public final native void notify()",
                "public final native void notifyAll()",
                "public java.lang.String toString()",
                "public final void wait() throws java.lang.InterruptedException",
                "public final native void wait(long arg0) throws java.lang.InterruptedException",
                "public final void wait(long arg0, int arg1) throws java.lang.InterruptedException"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList(
                "A", "B", "C extends com.github.zerkseez.reflection.types.ComplexGenericClass<B, A, C>"
        );
    }
}
