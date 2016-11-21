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
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.zerkseez.reflection.types.GenericInterface;

public class StaticAnonymousClassReflectionTest2 extends AbstractReflectionTest {
    private static GenericInterface<?> getTestSubject() {
        return new GenericInterface<Short>() {
            @Override
            public Short getT() {
                return null;
            }

            @Override
            public List<Short> getListOfT() {
                return null;
            }

            @Override
            public List<List<Short>> getListOfListOfT() {
                return null;
            }

            @Override
            public List<Set<? super Short>> getListOfSetOfWildcardSuperT() {
                return null;
            }

            @Override
            public List<Set<? extends Short>> getListOfSetOfWildcardExtendsT() {
                return null;
            }
        };
    }
    
    @Override
    public Class<?> getType() {
        return getTestSubject().getClass();
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.StaticAnonymousClassReflectionTest2$1";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(Object.class.getName());
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.GenericInterface<java.lang.Short>"
        );
    }

    @Override
    public Collection<String> getExpectedPublicFields() {
        return Arrays.asList();
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList();
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public java.util.List<java.util.List<java.lang.Short>> getListOfListOfT()",
                "public java.util.List<java.util.Set<? extends java.lang.Short>> getListOfSetOfWildcardExtendsT()",
                "public java.util.List<java.util.Set<? super java.lang.Short>> getListOfSetOfWildcardSuperT()",
                "public java.util.List<java.lang.Short> getListOfT()",
                "public java.lang.Short getT()",
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
        return Arrays.asList();
    }
    
    @Test
    public void testIsAnonymousClass() {
        Assert.assertTrue(getTypeInfo().isAnonymousClass());
    }
}
