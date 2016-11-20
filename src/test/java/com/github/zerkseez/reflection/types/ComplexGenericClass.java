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
import java.util.Map;
import java.util.Set;

public class ComplexGenericClass<A, B, C extends ComplexGenericClass<B, A, C>>
        implements ComplexGenericInterface<A, B, C> {
    @Override
    public Map<A, B> getMapOfXY() {
        return null;
    }

    @Override
    public Map<B, A> getMapOfYX() {
        return null;
    }

    @Override
    public C getZ() {
        return null;
    }

    @Override
    public List<C> getListOfZ() {
        return null;
    }

    @Override
    public List<List<C>> getListOfListOfZ() {
        return null;
    }

    @Override
    public List<Set<? super C>> getListOfSetOfWildcardSuperZ() {
        return null;
    }

    @Override
    public List<Set<? extends C>> getListOfSetOfWildcardExtendsZ() {
        return null;
    }
}
