# Abstraction

**Abstraction** is an OOP principle that hides implementation details and exposes only the essential functionality. It allows us to focus on **what an object does rather than how it does it**.

Abstraction is commonly achieved using:

- Abstract Classes
- Interfaces

**Key Benefits of Abstraction**

- **Reduces Complexity**: Hides unnecessary implementation details.
- **Improves Maintainability**: Allows internal implementations to change without affecting code that uses them.
- **Supports Polymorphism**: Allows different implementations to be used through a common type.

### Abstraction Using Abstract Classes

An **abstract class** cannot be instantiated directly. It can contain abstract methods (without implementation) and concrete methods (with implementation).

Example:

```java
abstract class Vehicle {
    abstract void start();

    void stop() {
        System.out.println("Vehicle stopped");
    }
}

class Car extends Vehicle {

    @Override
    void start() {
        System.out.println("Car is starting");
    }
}
```

Usage

```java
Vehicle vehicle = new Car();
vehicle.start();
vehicle.stop();
```

Output

```text
Car is starting
Vehicle stopped
```

Here, `Vehicle` defines what a vehicle can do, while `Car` provides the implementation of `start()`.

### Abstraction Using Interfaces

An **interface** defines a contract that implementing classes must follow. It specifies the required behavior without dictating its implementation.

Example:

```java
interface Animal {
    void makeSound();
}

class Dog implements Animal {

    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat implements Animal {

    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}
```

Usage:

```java
Animal animal = new Dog();
animal.makeSound();

animal = new Cat();
animal.makeSound();
```

Output:

```text
Dog barks
Cat meows
```

Both classes implement the same interface but provide different implementations.

To learn more about interfaces [click here](/oop/interfaces/README.md)

### Abstract Class vs Interface

| Feature     | Abstract Class                    | Interface                                                   |
| ----------- | --------------------------------- | ----------------------------------------------------------- |
| Methods     | Abstract and concrete methods     | Abstract methods, plus supported default and static methods |
| Variables   | Can have instance variables       | Fields are constants (`public static final`)                |
| Constructor | Allowed                           | Not allowed                                                 |
| Inheritance | A class can extend only one class | A class can implement multiple interfaces                   |
| Purpose     | Share common state and behavior   | Define a common contract or capability                      |
