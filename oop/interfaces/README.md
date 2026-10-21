# Interfaces

In Object-Oriented Programming (OOP), an interface defines a set of methods that a class must implement. It specifies what a class must do, not how it does it.

Interfaces help achieve abstraction, polymorphism, and loose coupling. They also allow a class to implement multiple interfaces, supporting multiple inheritance of type.

**Key Characteristics of Interfaces**

- **Contract Definition**: Specifies the operations that implementing classes must provide.
- **Abstraction**: Exposes essential behavior without requiring users to know its implementation.
- **Polymorphism**: Allows different objects to be used through a common interface.
- **Multiple Inheritance of Type**: Allows a class to implement multiple interfaces in languages such as Java.
- **Loose Coupling**: Reduces dependencies on specific implementations, improving flexibility and testability.

### Defining and Implementing an Interface

An interface is defined using the language's interface declaration mechanism. A class implements the interface by providing the required method implementations.

**Define an Interface**

```java
interface Vehicle {
    void start();
    void stop();
}
```

The `Vehicle` interface defines two operations: `start()` and `stop()`. It specifies what a vehicle must be able to do without defining how those operations work.

**Implement the Interface**

```java
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping...");
    }
}
```

The `Car` class provides implementations for both methods declared by `Vehicle`.

**Using the Implemented Class**

```java
public class Main {
    public static void main(String[] args) {
        Vehicle myCar = new Car();

        myCar.start();
        myCar.stop();
    }
}
```

Output:

```text
Car is starting...
Car is stopping...
```

The variable `myCar` uses the `Vehicle` interface as its reference type, while the actual object is a `Car`. This demonstrates polymorphism because the implementation is determined by the object's actual type.

### Multiple Inheritance with Interfaces

Some programming languages, including Java, do not allow a class to inherit from multiple classes but allow it to implement multiple interfaces.

This enables a class to support multiple capabilities without inheriting implementation and state from multiple parent classes.

**Example: Implementing Multiple Interfaces**

```java
interface Flyable {
    void fly();
}

interface Drivable {
    void drive();
}

class FlyingCar implements Flyable, Drivable {
    @Override
    public void fly() {
        System.out.println("Flying car is flying...");
    }

    @Override
    public void drive() {
        System.out.println("Flying car is driving...");
    }
}
```

Here, `FlyingCar` implements both `Flyable` and `Drivable`, so it provides the behaviors required by both interfaces.

Usage:

```java
public class Main {
    public static void main(String[] args) {
        FlyingCar vehicle = new FlyingCar();

        vehicle.fly();
        vehicle.drive();
    }
}
```

Output:

```text
Flying car is flying...
Flying car is driving...
```

### Default and Static Methods in Interfaces

Some languages allow interfaces to contain methods with implementations in addition to abstract method declarations. Java supports default and static methods in interfaces.

#### Default Methods

A default method provides a predefined implementation that implementing classes can inherit or override.

```java
interface Animal {
    void sound();

    default void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
```

Usage:

```java
Dog dog = new Dog();
dog.sound();
dog.sleep();
```

Output:

```text
Dog barks
Sleeping...
```

The `Dog` class implements `sound()` but inherits the default implementation of `sleep()`.

#### Static Methods

A static method belongs to the interface itself rather than to an implementing object's instance.

```java
interface MathOperations {
    static int add(int a, int b) {
        return a + b;
    }
}
```

Usage:

```java
int result = MathOperations.add(5, 10);
System.out.println("Sum: " + result);
```

Output:

```text
Sum: 15
```

Static interface methods are called using the interface name.

**Why Use Interfaces?**

- Allows different classes to follow a common contract.
- Supports polymorphism and multiple inheritance of type.
- Reduces dependencies on concrete implementations.
- Makes code easier to extend, test, and maintain.
- Separates required behavior from implementation details.
