package com.github.zerkseez.reflection;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.zerkseez.reflection.types.GenericInterfaceWithBounds;
import com.github.zerkseez.reflection.types.SubClass;

public class TypeVariableSubstitutionTest extends AbstractReflectionTest {
    @Override
    public Class<?> getType() {
        return GenericInterfaceWithBounds.class;
    }
    
    @Override
    public TypeInfo<?> getTypeInfo() {
        return super.getTypeInfo().substituteTypeVariableValues(
                super.getTypeInfo().getTypeVariables().stream()
                        .map(i -> i.withValue(Reflection.getTypeInfo(SubClass.class)))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String getExpectedStringRepresentation() {
        return "com.github.zerkseez.reflection.types.GenericInterfaceWithBounds<com.github.zerkseez.reflection.types.SubClass>";
    }

    @Override
    public List<String> getExpectedSuperClasses() {
        return Arrays.asList();
    }

    @Override
    public Collection<String> getExpectedInterfaces() {
        return Arrays.asList();
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
                "public abstract java.util.List<java.util.List<com.github.zerkseez.reflection.types.SubClass>> getListOfListOfT()",
                "public abstract java.util.List<java.util.Set<? extends com.github.zerkseez.reflection.types.SubClass>> getListOfSetOfWildcardExtendsT()",
                "public abstract java.util.List<java.util.Set<? super com.github.zerkseez.reflection.types.SubClass>> getListOfSetOfWildcardSuperT()",
                "public abstract java.util.List<com.github.zerkseez.reflection.types.SubClass> getListOfT()",
                "public abstract com.github.zerkseez.reflection.types.SubClass getT()"
        );
    }

    @Override
    public Collection<String> getExpectedTypeVariables() {
        return Arrays.asList("com.github.zerkseez.reflection.types.SubClass");
    }
}
