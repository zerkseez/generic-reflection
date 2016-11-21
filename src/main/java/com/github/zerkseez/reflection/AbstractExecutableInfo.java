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

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractExecutableInfo<T extends Executable> extends AbstractMemberInfo {
    private final T executable;
    private final Cache<List<ParameterInfo>> parameters;
    private final Cache<List<TypeInfo<?>>> exceptionTypes;
    private final Cache<String> signature;
    private final Cache<String> defaultStringRepresentation;

    public AbstractExecutableInfo(final TypeInfo<?> declaringElement, final T executable) {
        super(declaringElement);
        this.executable = executable;
        this.parameters = new Cache<List<ParameterInfo>>(this, () -> doGetParameters());
        this.exceptionTypes = new Cache<List<TypeInfo<?>>>(this, () -> doGetExceptionTypes());
        this.signature = new Cache<String>(this, () -> doGetSignature());
        this.defaultStringRepresentation = new Cache<String>(this, () -> doGetDefaultStringRepresentation());
    }
    
    /**
     * Gets the underlying executable object
     * 
     * @return The underlying executable object
     */
    public T getExecutable() {
        return executable;
    }

    @Override
    protected int doGetModifiers() {
        return getExecutable().getModifiers();
    }
    
    /**
     * Gets the list of parameters
     * 
     * @return The list of parameters
     */
    public final List<ParameterInfo> getParameters() {
        return Collections.unmodifiableList(parameters.get());
    }

    protected List<ParameterInfo> doGetParameters() {
        return Arrays.stream(getExecutable().getParameters())
                .map(i -> new ParameterInfo(
                        i, resolveActualType(Reflection.getTypeInfo(i.getParameterizedType()))
                ))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets the list of exception types
     * 
     * @return The list of exception types
     */
    public final List<TypeInfo<?>> getExceptionTypes() {
        return Collections.unmodifiableList(exceptionTypes.get());
    }

    protected List<TypeInfo<?>> doGetExceptionTypes() {
        return Arrays.stream(getExecutable().getGenericExceptionTypes())
                .map(i -> Reflection.getTypeInfo(i))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets the signature of this executable
     * 
     * @return The signature of this executable
     */
    public final String getSignature() {
        return signature.get();
    }

    protected abstract String doGetSignature();

    @Override
    protected List<TypeVariableInfo> doGetDeclaredTypeVariables() {
        return Arrays.stream(getExecutable().getTypeParameters())
                .map(i -> new TypeVariableInfo(i))
                .collect(Collectors.toList());
    }

    /**
     * Gets the default string representation of this executable
     * 
     * @return The default string representation of this executable
     */
    @Override
    public final String toString() {
        return defaultStringRepresentation.get();
    }
    
    protected abstract String doGetDefaultStringRepresentation();
    
    @Override
    protected AnnotatedElement getAnnotatedElement() {
        return getExecutable();
    }
}
