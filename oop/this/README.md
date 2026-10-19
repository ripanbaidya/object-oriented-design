# 'this' keyword

The `this` keyword is a fundamental concept in Object-Oriented Programming (OOP) that provides a reference to the **current object**.

Its behavior and usage are crucial in Java programming for building robust and maintainable applications.

**This Keyword in Java:**

> The "this" keyword in Java is a built-in reference to the current object within a class's methods or constructors. It allows access to the object's properties, methods, and other members.

**It is mainly used to:**

- Differentiate instance variables from parameters
- Call another constructor (this())
- Pass current object as argument
- Return current object
- Call current class method explicitly

## Uses of 'this'

**Resolve Variable Shadowing**

The "this" keyword is crucial when differentiating between **instance variables** and **method or constructor parameters** that share the same name.

In such cases, it explicitly refers to the instance variable of the current object, avoiding ambiguity. This is particularly helpful in constructors and setter methods where parameters often shadow instance variables.

```
Instance variable = Parameter variable
```

```java
class User {

  String name;

  User(String name) {
    // Instance variable = Parameter variable
    this.name = name;
  }
}
```

**Call another constructor (Constructor Chaining)**

Using the "this" keyword for constructor chaining allows one constructor to invoke another constructor within the same class.

This reduces code duplication and centralizes initialization logic, making the code easier to maintain.

It also ensures that all constructors eventually delegate to a common initialization point.

**Notes:**

- Must be first statement
- Only one constructor call allowed

```java
class User {

  String name;
  int age;

  // Constructor 1
  User(String name) {
    this.name = name;
  }

  // Constructor 2
  User(String name, int age) {
    this(name);   // calls constructor 1
    this.age = age;
  }

  void display() {
    System.out.println(name + " " + age);
  }
}
```

**Call Current Class Method**

```java
class A {

  void display() {
    System.out.println("Display method");
  }

  void show() {
    this.display();  // Optional
  }
}
```

Here this.display() is optional, Compiler automatically assumes this.

**Passing the Current Object**

`this` can be used to pass the current object as a parameter to another method.

```java
class Student {

  void print(Student obj) {
    System.out.println("Student passed");
  }

    void show() {
        print(this);  // passing current object
    }
}
```

**Return Current Object (Method Chaining)**

The "this" keyword can be used to return the current instance of a class.

This approach is commonly used in implementing **fluent interfaces**, **Builder Pattern**, **Stream like API**

This pattern enhances code readability and provides a seamless way to chain method calls.

```java
class Calculator {
  int value;

  Calculator add(int num) {
    this.value += num;
    return this;
  }

  Calculator sub(int num) {
    this.value -= num;
    return this;
  }

  void result() {
    System.out.println("Current value: " + value);
  }

}
class Main {
  public static void main(String[] args) {

    new Calculator().add(10).sub(3).result(); // 7
  }
}
```

## Where not to use 'this'

**Static context**

static belongs to class, not object. and here We can't access non-static context from the static method.

```java
class Test {
  int num = 10;

  static void display() {
    System.out.println(num);  // error
  }
}
```

**Output**

```
java: non-static variable num cannot be referenced from a static context
```

## Advantages & Disadvantages

### Advantages

1. **Resolves Variable Shadowing:** When instance variable and parameter have same name.
2. **Enables Constructor Chaining:** Reduces code duplication.
3. **Supports Method Chaining (Fluent API):** Used heavily in modern APIs. as well as
   In Builder Pattern, Streams, Logging frameworks, Modern APIs.
4. **Pass Current Object:** Useful in event-driven design and Used in callbacks

### Disadvantages

1. **Overuse Reduces Readability:** Excessive or unnecessary use of "this" can make code verbose and harder to read, especially when there is no risk of variable shadowing.
2. **Limited to Instance Context:** "this" cannot be used in static methods or contexts, which can be restrictive in certain situations.

## Conclusion

- `this` is not just a keyword — it represents the current object reference.
- It is essential for:
  - Proper initialization
  - Constructor chaining
  - Fluent API design
  - Clean object-oriented design

## Resources

- https://www.geeksforgeeks.org/java/java-this-keyword/
