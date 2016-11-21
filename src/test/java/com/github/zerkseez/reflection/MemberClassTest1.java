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

import org.junit.Assert;
import org.junit.Test;

import com.github.zerkseez.reflection.types.SimpleClass;

public class MemberClassTest1 extends AbstractReflectionTest {
    public class MemberClass extends SimpleClass {
        public MemberClass(int arg0) {
        }
    }

    @Override
    public Class<?> getType() {
        return MemberClass.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.MemberClassTest1.MemberClass";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.SimpleClass",
                Object.class.getName()
        );
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Arrays.asList();
    }

    @Override
    public Collection<String> getExpectedPublicFields() {
        return Arrays.asList(
                "public int simpleClassPublicPrimitiveIntField",
                "public int[] simpleClassPublicPrimitiveIntArrayField",
                "public java.lang.String simpleClassPublicStringField",
                "public java.lang.String[] simpleClassPublicStringArrayField"
        );
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList(
                "public MemberClass(com.github.zerkseez.reflection.MemberClassTest1 arg0, int arg1)"
        );
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public int getSimpleClassPrivatePrimitiveIntField()",
                "public java.lang.String getSimpleClassPrivateStringField()",
                "public native int hashCode()",
                "public final native void notify()",
                "public final native void notifyAll()",
                "public void setSimpleClassPrivatePrimitiveIntField(int arg0)",
                "public void setSimpleClassPrivateStringField(java.lang.String arg0)",
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
    public void testIsMemberClass() {
        Assert.assertTrue(getTypeInfo().isMemberClass());
    }
}