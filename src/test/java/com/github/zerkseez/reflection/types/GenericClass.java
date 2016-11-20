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
package com.github.zerkseez.reflection.types;

import java.util.List;
import java.util.Set;

public class GenericClass<T> {
    public static <T> void testOverloading(final T[][] twoDimensionArrayOfT) {
    }

    public T publicGenericField = null;
    protected T protectedGenericField = null;
    private T privateGenericField = null;

    public T getT() {
        return privateGenericField;
    }

    public List<T> getListOfT() {
        return null;
    }

    public List<List<T>> getListOfListOfT() {
        return null;
    }

    public List<Set<? super T>> getListOfSetOfWildcardSuperT() {
        return null;
    }

    public List<Set<? extends T>> getListOfSetOfWildcardExtendsT() {
        return null;
    }

    public void doSomethingWithT(final T p0,
                                 final T[] p1,
                                 final T[][] p2,
                                 final T[][][] p3,
                                 final List<T> p4,
                                 final List<T[]> p5,
                                 final List<List<T>> p6,
                                 final List<List<T>[]> p7,
                                 final List<List<T[]>> p8,
                                 final List<List<T[]>[]> p9,
                                 final List<List<? extends T>> p10,
                                 final List<List<? extends T[]>> p11,
                                 final List<List<? extends T>>[] p12,
                                 final List<List<? super T>> p13) {
    }

    @SuppressWarnings("hiding")
    public <T> T testTypeVariableHiding(final List<T> parameter) {
        return null;
    }

    public T[] testOverloading() {
        return null;
    }

    public List<T> testOverloading(final T parameter) {
        return null;
    }

    public T testOverloading(final T[] parameter) {
        return null;
    }

    public T testOverloading(final List<T> parameter) {
        return null;
    }
}
