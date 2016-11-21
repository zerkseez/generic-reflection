package com.github.zerkseez.reflection;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.zerkseez.reflection.types.GenericInterface;
import com.github.zerkseez.reflection.types.SimpleInterface;

public class GenericLocalClassReflectionTest2 extends AbstractReflectionTest {
    private static class WrapperClass<T extends SimpleInterface> {
        public GenericInterface<?> getTestSubject() {
            class LocalClass implements GenericInterface<T> {
                @SuppressWarnings("unused")
                public void doSomething(T arg0) {
                }
                
                @Override
                public T getT() {
                    return null;
                }
    
                @Override
                public List<T> getListOfT() {
                    return null;
                }
    
                @Override
                public List<List<T>> getListOfListOfT() {
                    return null;
                }
    
                @Override
                public List<Set<? super T>> getListOfSetOfWildcardSuperT() {
                    return null;
                }
    
                @Override
                public List<Set<? extends T>> getListOfSetOfWildcardExtendsT() {
                    return null;
                }
            };
            
            return new LocalClass();
        }
    }
    
    @Override
    public Class<?> getType() {
        return (new WrapperClass<SimpleInterface>()).getTestSubject().getClass();
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.GenericLocalClassReflectionTest2$WrapperClass$1LocalClass";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList(Object.class.getName());
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Arrays.asList(
                "com.github.zerkseez.reflection.types.GenericInterface<T extends com.github.zerkseez.reflection.types.SimpleInterface>"
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
                "public void doSomething(T extends com.github.zerkseez.reflection.types.SimpleInterface arg0)",
                "public boolean equals(java.lang.Object arg0)",
                "public final native java.lang.Class<?> getClass()",
                "public java.util.List<java.util.List<T extends com.github.zerkseez.reflection.types.SimpleInterface>> getListOfListOfT()",
                "public java.util.List<java.util.Set<? extends T extends com.github.zerkseez.reflection.types.SimpleInterface>> getListOfSetOfWildcardExtendsT()",
                "public java.util.List<java.util.Set<? super T extends com.github.zerkseez.reflection.types.SimpleInterface>> getListOfSetOfWildcardSuperT()",
                "public java.util.List<T extends com.github.zerkseez.reflection.types.SimpleInterface> getListOfT()",
                "public T extends com.github.zerkseez.reflection.types.SimpleInterface getT()",
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
    public void testIsLocalClass() {
        Assert.assertTrue(getTypeInfo().isLocalClass());
    }
}