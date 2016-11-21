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

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Interface for obtaining type information
 * 
 * @author xerxes
 *
 * @param <T>
 *            The underlying java.lang.reflect.Type object type
 */
public interface TypeInfo<T extends Type> extends ElementInfo, HasTypeVariables {
    /**
     * Gets the underlying type this object represents
     * 
     * @return The underlying type this object represents
     */
    T getType();

    /**
     * Gets the raw underlying class
     * 
     * @return The raw underlying class if this TypeInfo represents a class or a
     *         parameterized type
     */
    Class<?> getErasedClass();

    /**
     * Equivalent to Class.getName()
     * 
     * @return The full binary name of this type
     */
    String getFullBinaryName();

    /**
     * Equivalent to Class.getCanonicalName()
     * 
     * @return The full canonical name of this type
     */
    String getFullCanonicalName();

    /**
     * Equivalent to Class.getSimpleName()
     * 
     * @return The simple name of this type
     */
    String getSimpleName();

    /**
     * Equivalent to Class.getPackage().getName()
     * 
     * @return The package name of this type
     */
    String getPackageName();

    /**
     * Gets modifiers for this type
     * 
     * @return The modifiers for this type
     */
    int getModifiers();

    /**
     * Gets the super-class of this type
     * 
     * @return The super-class of this type
     */
    TypeInfo<?> getSuperClass();

    /**
     * Gets the interfaces implemented by this type; interfaces implemented by
     * super-class or extended by implemented interfaces are NOT included
     * 
     * @return The interfaces implemented by this type
     */
    List<TypeInfo<?>> getInterfaces();

    /**
     * Gets all public fields
     * 
     * @return All public fields
     */
    List<FieldInfo> getPublicFields();

    /**
     * Gets public field by its name
     * 
     * @param name
     *            The field name
     * @return The field with the specified name; null if not found
     */
    FieldInfo getPublicField(String name);

    /**
     * Gets all declared fields
     * 
     * @return All declared fields
     */
    List<FieldInfo> getDeclaredFields();

    /**
     * Gets all public constructors
     * 
     * @return All public constructors
     */
    List<ConstructorInfo> getPublicConstructors();

    /**
     * Gets all declared constructors
     * 
     * @return All declared constructors
     */
    List<ConstructorInfo> getDeclaredConstructors();

    /**
     * Gets all public methods
     * 
     * @return All public methods
     */
    List<MethodInfo> getPublicMethods();

    /**
     * Gets all declared methods
     * 
     * @return All declared methods
     */
    List<MethodInfo> getDeclaredMethods();

    /**
     * Equivalent to Class.isPrimitive()
     * 
     * @return true if the type this object represents is primitive; false
     *         otherwise
     */
    boolean isPrimitive();

    /**
     * Equivalent to Class.isArray()
     * 
     * @return true if the type this object represents is an array type; false
     *         otherwise
     */
    boolean isArray();

    /**
     * Gets the element type
     * 
     * @return The element type if this object represents an array type; null
     *         otherwise
     */
    TypeInfo<?> getArrayElementType();

    /**
     * Equivalent to Class.isEnum()
     * 
     * @return true if the type this object represents is an enum type; false
     *         otherwise
     */
    boolean isEnum();

    /**
     * Gets the enum values
     * 
     * @return The enum values of this enum type; null if this object does not
     *         represent an enum type
     */
    List<Object> getEnumValues();

    /**
     * Equivalent to Class.isInterface()
     * 
     * @return true if the type this object represents is an interface; false
     *         otherwise
     */
    boolean isInterface();

    /**
     * Equivalent to Class.isAnnotation()
     * 
     * @return true if the type this object represents is an annotation; false
     *         otherwise
     */
    boolean isAnnotation();

    /**
     * Equivalent to Class.isAnonymousClass()
     * 
     * @return true if the type this object represents is an anonymous class;
     *         false otherwise
     */
    boolean isAnonymousClass();

    /**
     * Equivalent to Class.isMemberClass()
     * 
     * @return true if the type this object represents is a member class; false
     *         otherwise
     */
    boolean isMemberClass();

    /**
     * Equivalent to Class.isLocalClass()
     * 
     * @return true if the type this object represents is a local class; false
     *         otherwise
     */
    boolean isLocalClass();

    /**
     * Equivalent to Class.isSynthetic()
     * 
     * @return true if the type this object represents is synthetic; false
     *         otherwise
     */
    boolean isSynthetic();

    /**
     * Checks if the type this object represents is a type variable
     * 
     * @return true if the type this object represents is a type variable; false
     *         otherwise
     */
    boolean isTypeVariable();

    /**
     * Gets the name of this type variable
     * 
     * @return The name of this type variable; null if this object does not
     *         represent a type variable
     */
    String getTypeVariableName();

    /**
     * Gets the value of this type variable
     * 
     * @return The value of this type variable; null if this object does not
     *         represent a type variable or if the value is not set
     */
    TypeInfo<?> getTypeVariableValue();

    /**
     * Checks if the type this object represents is a wildcard type
     * 
     * @return true if the type this object represents is a wildcard type; false
     *         otherwise
     */
    boolean isWildcardType();

    /**
     * Gets the extends bounds for this type variable or wildcard type
     * 
     * @return The extends bounds for this type variable or wildcard type; null
     *         if this object does not represent a type variable nor a wildcard
     *         type, or if there is no extends bound
     */
    List<TypeInfo<?>> getExtendsBounds();

    /**
     * Gets the super bounds for this wildcard type
     * 
     * @return The super bounds for this wildcard type; null if this object does
     *         not represent a wildcard type, or if there is no super bound
     */
    List<TypeInfo<?>> getSuperBounds();

    /**
     * Gets the string representation of this type using the specified context
     * 
     * @param context
     *            The context object
     * @param includeTypeVariables
     *            Specifies whether type variables should be included
     * @return The string representation of this type
     */
    String toString(final ToStringContext context, final boolean includeTypeVariables);

    /**
     * Substitutes the values of type variable(s) this type represents or
     * references with the specified values. For example, if this object
     * represents java.util.List&lt;T&gt;, and the provided list has a type
     * variable "T" with value "java.lang.String", this method will return a
     * TypeInfo object that represents java.util.List&lt;java.lang.String&gt;.
     * 
     * @param typeVariables
     *            List of type variables with the desired values
     * @return The substitution result
     */
    TypeInfo<?> substituteTypeVariableValues(final List<TypeVariableInfo> typeVariables);

    /**
     * Maintains context information for TypeInfo.toString()
     * 
     * @author xerxes
     */
    public static interface ToStringContext {
        boolean isImported(String className);

        boolean isTypeVariableDefined(String typeVariableId);

        void defineTypeVariable(String typeVariableId);
    }

    /**
     * Default implementation of TypeInfo.ToStringContext
     * 
     * @author xerxes
     */
    public static class DefaultToStringContext implements ToStringContext {
        private final Set<String> definedTypeVariables = new HashSet<String>();

        @Override
        public boolean isImported(final String className) {
            return false;
        }

        @Override
        public boolean isTypeVariableDefined(final String typeVariableId) {
            if (definedTypeVariables.contains(typeVariableId)) {
                return true;
            }
            return false;
        }

        @Override
        public void defineTypeVariable(final String typeVariableId) {
            definedTypeVariables.add(typeVariableId);
        }
    }
}
