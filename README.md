# generic-reflection
Java reflection with generic resolver

## Synopsis
This library makes it easy to obtain type information including generic type parameter bindings easily at runtime.

For example:
```java
class BaseClass<A, B> {
    public Map<A, List<B>> doSomethingCrazy(A[] arg1, Collection<? extends B>[] arg2) { ... }
}

class SubClass extends BaseClass<String, Integer> {
}
```

To find out the return type and parameter types of SubClass.doSomethingCrazy():
```java
TypeInfo<?> typeInfo = Reflection.getTypeInfo(SubClass.class);
MethodInfo methodInfo = typeInfo.getPublicMethods().get(0);

TypeInfo<?> returnType = methodInfo.getReturnType();
System.out.println(returnType.toString()); // Prints java.util.Map<java.lang.String, java.util.List<java.lang.Integer>>

List<ParameterInfo> parameters = methodInfo.getParameters();
System.out.println(parameters.get(0).getType().toString()); // Prints java.lang.String[]
System.out.println(parameters.get(1).getType().toString()); // Prints java.util.Collection<? extends java.lang.Integer>[]
```

## Setup
### Maven
```xml
<dependency>
    <groupId>com.github.zerkseez</groupId>
    <artifactId>generic-reflection</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Basic Use Cases
To obtain information for any type:
```java
TypeInfo<?> typeInfo1 = Reflection.getTypeInfo(MyClass.class);
TypeInfo<?> typeInfo2 = Reflection.getTypeInfo("com.mycompany.MyClass");
```

Get all public methods, including inherited ones:
```java
List<MethodInfo> publicMethods = typeInfo.getPublicMethods();
```

Get return type and the parameter types for a method:
```java
MethodInfo method = ...
TypeInfo<?> returnType = method.getReturnType();
List<ParameterInfo> parameters = method.getParameters();
for (ParameterInfo parameter : parameters) {
    TypeInfo<?> parameterType = parameter.getType();
    ...
}
```

## License
Apache License, Version 2.0
