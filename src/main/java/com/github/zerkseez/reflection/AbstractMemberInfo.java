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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractMemberInfo extends AbstractElementInfo implements HasTypeVariables {
	private final ElementInfo declaringElement;
	
	protected AbstractMemberInfo(final ElementInfo declaringElement) {
		this.declaringElement = declaringElement;
	}

	public ElementInfo getDeclaringElement() {
		return declaringElement;
	}
	
	public abstract String getName();
	
	@Override
	public boolean hasTypeVariables() {
		return !getTypeVariables().isEmpty();
	}
	
	@Override
	public List<TypeVariableInfo> getTypeVariables() {
		if (getDeclaringElement() instanceof HasTypeVariables) {
			return ((HasTypeVariables)getDeclaringElement()).getTypeVariables();
		}
		return Collections.emptyList();
	}

	protected TypeInfo<?> resolveActualType(final TypeInfo<?> type) {
		if (type instanceof TypeVariableInfo) {
			for (int i = 0; i < getTypeVariables().size(); i++) {
				final TypeVariableInfo typeVariable = getTypeVariables().get(i);
				if (type.getId().equals(typeVariable.getId()) && typeVariable.getTypeVariableValue() != null) {
					return typeVariable.getTypeVariableValue();
				}
			}
			if (type.getTypeVariableValue() != null) {
				return resolveActualType(type.getTypeVariableValue());
			}
		}
		else if (type instanceof GenericArrayInfo) {
			return new GenericArrayInfo(
					((GenericArrayInfo)type).getType(),
					resolveActualType(type.getArrayElementType())
			);
		}
		else if (type instanceof ParameterizedTypeInfo) {
			final List<TypeVariableInfo> typeVariables = new ArrayList<TypeVariableInfo>();
			for (TypeVariableInfo typeVariable : type.getTypeVariables()) {
				typeVariables.add(typeVariable.withValue(resolveActualType(typeVariable.getTypeVariableValue())));
			}
			return new ParameterizedTypeInfo(((ParameterizedTypeInfo)type).getType(), typeVariables);
		}
		else if (type instanceof WildcardTypeInfo) {
			final List<TypeInfo<?>> superBounds = new ArrayList<TypeInfo<?>>();
			final List<TypeInfo<?>> extendsBounds = new ArrayList<TypeInfo<?>>();
			for (TypeInfo<?> superBound : type.getSuperBounds()) {
				superBounds.add(resolveActualType(superBound));
			}
			for (TypeInfo<?> extendsBound : type.getExtendsBounds()) {
				extendsBounds.add(resolveActualType(extendsBound));
			}
			return new WildcardTypeInfo(((WildcardTypeInfo)type).getType(), extendsBounds, superBounds);
		}
        return type;
    }
}
