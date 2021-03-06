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
import java.util.Collections;
import java.util.List;

import com.github.zerkseez.reflection.types.GenericSubSubClass;

public class GenericSubSubClassReflectionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return GenericSubSubClass.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.GenericSubSubClass";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.GenericSubClassWithGenericTypeParameter<java.lang.Short>",
                "com.github.zerkseez.reflection.types.GenericClass<java.util.List<java.lang.Short>>",
                Object.class.getName()
        );
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> getExpectedPublicFields() {
        return Arrays.asList(
                "public java.util.List<java.lang.Short> publicGenericField"
        );
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList(
                "public GenericSubSubClass()"
        );
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public void doSomethingWithT(java.util.List<java.lang.Short> arg0, java.util.List<java.lang.Short>[] arg1, java.util.List<java.lang.Short>[][] arg2, java.util.List<java.lang.Short>[][][] arg3, java.util.List<java.util.List<java.lang.Short>> arg4, java.util.List<java.util.List<java.lang.Short>[]> arg5, java.util.List<java.util.List<java.util.List<java.lang.Short>>> arg6, java.util.List<java.util.List<java.util.List<java.lang.Short>>[]> arg7, java.util.List<java.util.List<java.util.List<java.lang.Short>[]>> arg8, java.util.List<java.util.List<java.util.List<java.lang.Short>[]>[]> arg9, java.util.List<java.util.List<? extends java.util.List<java.lang.Short>>> arg10, java.util.List<java.util.List<? extends java.util.List<java.lang.Short>[]>> arg11, java.util.List<java.util.List<? extends java.util.List<java.lang.Short>>>[] arg12, java.util.List<java.util.List<? super java.util.List<java.lang.Short>>> arg13)",
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public java.util.List<java.util.List<java.util.List<java.lang.Short>>> getListOfListOfT()",
                "public java.util.List<java.util.Set<? extends java.util.List<java.lang.Short>>> getListOfSetOfWildcardExtendsT()",
                "public java.util.List<java.util.Set<? super java.util.List<java.lang.Short>>> getListOfSetOfWildcardSuperT()",
                "public java.util.List<java.util.List<java.lang.Short>> getListOfT()",
                "public java.util.List<java.lang.Short> getT()",
                "public native int hashCode()",
                "public final native void notify()",
                "public final native void notifyAll()",
                "public java.util.List<java.lang.Short> testOverloading(java.util.List<java.lang.Short>[] arg0)",
                "public java.util.List<java.lang.Short> testOverloading(java.util.List<java.util.List<java.lang.Short>> arg0)",
                "public java.util.List<java.lang.Short>[] testOverloading()",
                "public java.util.List<java.util.List<java.lang.Short>> testOverloading(java.util.List<java.lang.Short> arg0)",
                "public <T> T testTypeVariableHiding(java.util.List<T> arg0)",
                "public java.lang.String toString()",
                "public final void wait() throws java.lang.InterruptedException",
                "public final native void wait(long arg0) throws java.lang.InterruptedException",
                "public final void wait(long arg0, int arg1) throws java.lang.InterruptedException"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList();
    }
}
