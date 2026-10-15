# LoD - Law of Demeter

The **Law of Demeter (LoD)**, also known as the **Principle of Least Knowledge**, is a software design guideline for developing object-oriented programs.

In simple terms, its core philosophy is: **"Don't talk to strangers. Only talk to your immediate friends."**

An object should assume as little as possible about the structure or properties of anything else, including its subcomponents. This keeps your code loosely coupled, making it much easier to maintain, test, and change later.

## The Rule of Thumb

If you look at a piece of code, a prominent sign of violating the Law of Demeter is a long chain of method calls (sometimes called _"train wrecks"_).

> Violation Look: object.getA().getB().getC().doSomething();

Instead of digging deep into another object's internal structure, you should just ask the immediate object to do the work for you.

### The Formal Rules

Specifically, a method `M` in an object `O` should only call methods belonging to:

1. Object `O` itself (its own methods).
2. The parameters passed into method `M`.
3. Any objects created or instantiated inside method `M`.
4. Any direct component objects (instance variables/fields) of object `O`.

## Example

Let’s look at a scenario involving a `Driver`, a `Car`, an `Engine`, and a `SparkPlug`.

### The Bad Way

Here, the `Driver` wants to start the car, but to do so, it reaches deep into the `Car` to get the `Engine`, and then reaches into the `Engine` to get the `SparkPlug`.

```java
public class SparkPlug {
    public void ignite() {
        System.out.println("Spark ignited.");
    }
}

public class Engine {
    private SparkPlug sparkPlug = new SparkPlug();
    public SparkPlug getSparkPlug() { return sparkPlug; }
}

public class Car {
    private Engine engine = new Engine();
    public Engine getEngine() { return engine; }
}

// The Violator
public class Driver {
    public void drive(Car car) {
        // VIOLATION: Driver is talking to SparkPlug (a stranger) through Engine
        car.getEngine().getSparkPlug().ignite();
    }
}
```

**Why this is bad:** If you decide to change how the `Engine` works tomorrow (e.g., replacing the `SparkPlug` with an electric motor system), the `Driver` class breaks. The `Driver` shouldn't need to know that cars even have spark plugs.

### The Good Way

To fix this, we delegate the responsibility down the chain. The `Driver` asks the `Car` to start, the `Car` asks its `Engine` to start, and the `Engine` handles its own `SparkPlug`.

```java
public class SparkPlug {
    public void ignite() {
        System.out.println("Spark ignited.");
    }
}

public class Engine {
    private SparkPlug sparkPlug = new SparkPlug();

    public void start() {
        sparkPlug.ignite(); // Engine talks to its direct component
    }
}

public class Car {
    private Engine engine = new Engine();

    public void start() {
        engine.start(); // Car talks to its direct component
    }
}

// The Clean Code
public class Driver {
    public void drive(Car car) {
        car.start(); // OK: Driver only talks to Car (an immediate friend)
    }
}
```

## Why It Matters

- **Easier Maintenance:** If the internal structure of `Engine` changes, you only modify `Car`. The `Driver` remains completely untouched.

- **Higher Reusability:** Objects are self-contained and don't rely on the complex web of how other objects are built.

- **Better Testability:** It is much easier to write mock objects for unit testing when a class only interacts with its direct neighbors.

_(Note: Method chaining is perfectly fine when using fluent interfaces or the Builder Pattern—such as `StringBuilder.append().append()`—because you are continuously operating on the same object or type, not navigating through a hierarchy of strangers.)_
