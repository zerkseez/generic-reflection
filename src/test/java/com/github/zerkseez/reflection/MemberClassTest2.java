package com.github.zerkseez.reflection;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.zerkseez.reflection.types.GenericClass;
import com.github.zerkseez.reflection.types.SimpleInterface;

public class MemberClassTest2 extends AbstractReflectionTest {
    public static class WrapperClass<A extends SimpleInterface, B> {
        public class MemberClass<X, Y> extends GenericClass<Y> {
            public A getWrapperA() {
                return null;
            }
            
            public List<B> getWrapperListOfB() {
                return null;
            }
        }
    }
    
    @Override
    public Class<?> getType() {
        return WrapperClass.MemberClass.class;
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.MemberClassTest2.WrapperClass.MemberClass<X, Y>";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.GenericClass<Y>",
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
                "public Y publicGenericField"
        );
    }
    
    @Override
    public Collection<String> getExpectedPublicConstructors() {
        return Arrays.asList(
                "public MemberClass(com.github.zerkseez.reflection.MemberClassTest2.WrapperClass<A extends com.github.zerkseez.reflection.types.SimpleInterface, B> arg0)"
        );
    }

    @Override
    public Collection<String> getExpectedPublicMethods() {
        return Arrays.asList(
                "public void doSomethingWithT(Y arg0, Y[] arg1, Y[][] arg2, Y[][][] arg3, java.util.List<Y> arg4, java.util.List<Y[]> arg5, java.util.List<java.util.List<Y>> arg6, java.util.List<java.util.List<Y>[]> arg7, java.util.List<java.util.List<Y[]>> arg8, java.util.List<java.util.List<Y[]>[]> arg9, java.util.List<java.util.List<? extends Y>> arg10, java.util.List<java.util.List<? extends Y[]>> arg11, java.util.List<java.util.List<? extends Y>>[] arg12, java.util.List<java.util.List<? super Y>> arg13)",
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public java.util.List<java.util.List<Y>> getListOfListOfT()",
                "public java.util.List<java.util.Set<? extends Y>> getListOfSetOfWildcardExtendsT()",
                "public java.util.List<java.util.Set<? super Y>> getListOfSetOfWildcardSuperT()",
                "public java.util.List<Y> getListOfT()",
                "public Y getT()",
                "public A extends com.github.zerkseez.reflection.types.SimpleInterface getWrapperA()",
                "public java.util.List<B> getWrapperListOfB()",
                "public native int hashCode()",
                "public final native void notify()",
                "public final native void notifyAll()",
                "public Y testOverloading(Y[] arg0)",
                "public Y testOverloading(java.util.List<Y> arg0)",
                "public Y[] testOverloading()",
                "public java.util.List<Y> testOverloading(Y arg0)",
                "public <T> T testTypeVariableHiding(java.util.List<T> arg0)",
                "public java.lang.String toString()",
                "public final void wait() throws java.lang.InterruptedException",
                "public final native void wait(long arg0) throws java.lang.InterruptedException",
                "public final void wait(long arg0, int arg1) throws java.lang.InterruptedException"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList("X", "Y");
    }
    
    @Test
    public void testIsMemberClass() {
        Assert.assertTrue(getTypeInfo().isMemberClass());
    }
}