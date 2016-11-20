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

public class SimpleClass {
    public int simpleClassPublicPrimitiveIntField;
    public int[] simpleClassPublicPrimitiveIntArrayField;
    public String simpleClassPublicStringField;
    public String[] simpleClassPublicStringArrayField;
    protected int simpleClassProtectedPrimitiveIntField;
    protected String simpleClassProtectedStringField;
    private int simpleClassPrivatePrimitiveIntField;
    private String simpleClassPrivateStringField;
    
    public SimpleClass() {
    }
    
    public SimpleClass(int intArg) {
    }
    
    public SimpleClass(int[] intArrayArg) {
    }
    
    protected SimpleClass(String stringArg) {
    }
    
    @SuppressWarnings("unused")
    private SimpleClass(String[] stringArrayArg) {
    }

    public int getSimpleClassPrivatePrimitiveIntField() {
        return simpleClassPrivatePrimitiveIntField;
    }

    public void setSimpleClassPrivatePrimitiveIntField(int simpleClassPrivatePrimitiveIntField) {
        this.simpleClassPrivatePrimitiveIntField = simpleClassPrivatePrimitiveIntField;
    }

    public String getSimpleClassPrivateStringField() {
        return simpleClassPrivateStringField;
    }

    public void setSimpleClassPrivateStringField(String simpleClassPrivateStringField) {
        this.simpleClassPrivateStringField = simpleClassPrivateStringField;
    }
}
