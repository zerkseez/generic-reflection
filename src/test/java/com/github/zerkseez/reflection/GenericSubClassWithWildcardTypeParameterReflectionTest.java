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

import com.github.zerkseez.reflection.types.GenericSubClassWithWildcardTypeParameter;

public class GenericSubClassWithWildcardTypeParameterReflectionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return GenericSubClassWithWildcardTypeParameter.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.GenericSubClassWithWildcardTypeParameter<T>";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.GenericClass<java.util.Set<? extends T>>",
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
                "public java.util.Set<? extends T> publicGenericField"
        );
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList(
                "public GenericSubClassWithWildcardTypeParameter()"
        );
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public void doSomethingWithT(java.util.Set<? extends T> arg0, java.util.Set<? extends T>[] arg1, java.util.Set<? extends T>[][] arg2, java.util.Set<? extends T>[][][] arg3, java.util.List<java.util.Set<? extends T>> arg4, java.util.List<java.util.Set<? extends T>[]> arg5, java.util.List<java.util.List<java.util.Set<? extends T>>> arg6, java.util.List<java.util.List<java.util.Set<? extends T>>[]> arg7, java.util.List<java.util.List<java.util.Set<? extends T>[]>> arg8, java.util.List<java.util.List<java.util.Set<? extends T>[]>[]> arg9, java.util.List<java.util.List<? extends java.util.Set<? extends T>>> arg10, java.util.List<java.util.List<? extends java.util.Set<? extends T>[]>> arg11, java.util.List<java.util.List<? extends java.util.Set<? extends T>>>[] arg12, java.util.List<java.util.List<? super java.util.Set<? extends T>>> arg13)",
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public java.util.List<java.util.List<java.util.Set<? extends T>>> getListOfListOfT()",
                "public java.util.List<java.util.Set<? extends java.util.Set<? extends T>>> getListOfSetOfWildcardExtendsT()",
                "public java.util.List<java.util.Set<? super java.util.Set<? extends T>>> getListOfSetOfWildcardSuperT()",
                "public java.util.List<java.util.Set<? extends T>> getListOfT()",
                "public java.util.Set<? extends T> getT()",
                "public native int hashCode()",
                "public final native void notify()",
                "public final native void notifyAll()",
                "public java.util.List<java.util.Set<? extends T>> testOverloading(java.util.Set<? extends T> arg0)",
                "public java.util.Set<? extends T> testOverloading(java.util.List<java.util.Set<? extends T>> arg0)",
                "public java.util.Set<? extends T> testOverloading(java.util.Set<? extends T>[] arg0)",
                "public java.util.Set<? extends T>[] testOverloading()",
                "public <T> T testTypeVariableHiding(java.util.List<T> arg0)",
                "public java.lang.String toString()",
                "public final void wait() throws java.lang.InterruptedException",
                "public final native void wait(long arg0) throws java.lang.InterruptedException",
                "public final void wait(long arg0, int arg1) throws java.lang.InterruptedException"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList("T");
    }
}
