# 'final' Keyword in Java

In Java, the `final` keyword is a non-access modifier used to restrict extensibility, modification, and reassignment. It serves as a tool for enforcing immutability, ensuring API security, and optimizing runtime performance.

The application of `final` fundamentally alters the behavior of **classes**, **methods**, and **variables**.

## Final Classes

A class declared as `final` **cannot be subclassed** or extended. This cuts off the inheritance tree at that specific node.

```java
final class Vehicle {
}

// class Car extends Vehicle { }
// Compile-time error: Cannot inherit from final 'utility.Vehicle'
```

- **Immutability Contracts:** Core Java library classes such as `java.lang.String`, `java.lang.Integer`, and `java.lang.Double` are marked `final`. If `String` were not final, a subclass could override behaviors to masquerade as a secure string while secretly altering data, compromising JVM security.
- **Performance Optimization:** The compiler and JVM can optimize method calls within `final` classes (e.g., via aggressive method inlining), knowing that no subclass will ever alter the behavior.

## Final Methods

A method declared as `final` **cannot be overridden** by any subclass.

```java
class Parent {
    public final void computeMetrics() {
        System.out.println("Hello.");
    }
}

class Child extends Parent {

    // @Override
    // public void computeMetrics() {}
    // Compile-time error - overridden method is final
}
```

- **Preserving Invariants:** Use `final` methods when the base class prescribes a rigid control loop or algorithmic sequence (e.g., the Template Method Design Pattern) that sub-classes must not corrupt.
- **Security Barriers:** Critical system check operations should be marked `final` to prevent malicious actors from extending the component and overriding security verifications to return `true`.

## Final Variables

Marking a variable `final` guarantees that its value mapping can be assigned **exactly once**. Once initialized, any attempt to rebind the variable results in a compile-time error.

### Primitive Variables

For primitives, `final` freezes the literal value itself.

```java
public void calculate() {
    final int baseThreshold = 100;

    // baseThreshold = 200;
    // Compile-time error
}
```

### Reference Variables

For reference variables, `final` freezes the **memory address pointer** held by the reference variable. It does **not** make the underlying heap object immutable.

```java
class Cat {
    private int weight;

    public void setWeight(int w) {
        this.weight = w;
    }
}

public class Main {
    public static void main(String[] args) {
        final Cat cat = new Cat(); // Allocated address e.g., @0x9f is locked into 'cat'

        // cat = new Cat();
        // Compile-time error: Cannot reassign final variable 'cat'

        cat.setWeight(5); // valid: Internal object state is mutable
    }
}
```

### Static Final Variables (Constants)

Used to create compile-time thread-safe constants. By convention, these are named using `UPPER_CASE` with underscore separators.

```java
public class Test {
    public static final double PI = 3.14159;
}
```

- `static`: Accessible via class metadata context without instantiating objects.
- `final`: Value cannot be altered post-class-loading.

Usage

```java
double piValue = Test.PI;
```

### Final Parameters

Declaring method arguments as `final` locks the local variable parameter inside the scope of the method execution thread frame, preventing accidental modification within the method body.

```java
public void processTransaction(final double orderTotal) {
    // orderTotal = orderTotal * 0.90;
    // Compile-time error: The final local variable orderTotal cannot be assigned
}
```

## Conclusion

Remember these three rules:

- final variable — cannot be reassigned.
- final method — cannot be overridden.
- final class — cannot be extended.

The most important distinction is that final prevents reassignment or inheritance; it does not automatically make an object immutable.
