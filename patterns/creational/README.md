# Creational Design Patterns

Creational design patterns deal with how objects are created. They help make object creation more flexible, reusable, and independent of the specific classes being instantiated.

Instead of creating objects directly everywhere using `new`, these patterns provide structured ways to control object creation based on the situation.

There are five main creational design patterns:

1. [Singleton Pattern](#singleton-pattern)
2. Factory Method
3. Abstract Factory
4. Builder
5. Prototype

For each, you need to understand the problem each pattern solves, when to use it, and how its implementation differs from the others.

## Singleton Pattern

The Singleton pattern is one of the most fundamental creational design patterns in software engineering. **It ensures that a class has only one instance throughout the application's lifecycle and provides a global point of access to that instance**.

**How it works**

- Make the constructor private to prevent external object creation.
- Create a single instance inside the class.
- Provide a method to access that instance.

Example:

```java
class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

Usage:

```java
Singleton a = Singleton.getInstance();
Singleton b = Singleton.getInstance();

System.out.println(a == b); // true
```

Both variables reference the same object.

**When to use it**

Use Singleton when an application needs exactly one shared instance, such as a configuration manager or a logger.

### Implementations

There are several ways to implement the Singleton pattern, each with distinct trade-offs regarding memory efficiency, thread safety, and performance.

**1. Eager Initialization**

The instance is created when the class is loaded.

```java
class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```

- Thread safety: Yes
- Performance: Fast; no synchronization needed.
- Memory: Instance is created even if never used.
- Use when: The instance is lightweight and always needed.

**2. Lazy Initialization**

The instance is created only when first requested.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

- Thread safety: No; multiple threads can create multiple instances.
- Performance: Fast, with no synchronization overhead.
- Memory: Instance is created only when needed.
- Use when: Only in single-threaded environments.

**3. Synchronized Method**

The entire `getInstance()` method is synchronized.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

- Thread safety: Yes
- Performance: Slower under contention because every call requires synchronization.
- Memory: Instance is created only when needed.
- Use when: Simplicity matters more than synchronization overhead.

**4. Double-Checked Locking**

Synchronization is used only when creating the instance.

```java
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

- Thread safety: Yes, with `volatile`.
- Performance: Fast after initialization; synchronization is avoided on subsequent calls.
- Memory: Instance is created only when needed.
- Use when: Lazy initialization and efficient concurrent access are required.

**5. Bill Pugh Singleton (Holder Pattern)**

Uses a static inner class to initialize the instance lazily.

```java
class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

- Thread safety: Yes, guaranteed by Java class initialization.
- Performance: Fast; no explicit synchronization needed.
- Memory: Instance is created only when first accessed.
- Use when: You want a simple, lazy, thread-safe Singleton.

**6. Enum Singleton**

Uses an enum with a single constant.

```java
enum Singleton {
    INSTANCE;

    public void execute() {
        System.out.println("Executing...");
    }
}
```

Usage: `Singleton.INSTANCE.execute();`

- Thread safety: Yes
- Performance: Fast; no explicit synchronization needed.
- Memory: Instance is created when the enum class is initialized.
- Security: Protects against standard Java serialization and reflection attacks.
- Use when: You want a robust Singleton, especially when serialization is a concern.

### Comparison

| Implementation         | Thread-safe | Lazy            | Performance               |
| ---------------------- | ----------- | --------------- | ------------------------- |
| Eager                  | Yes         | No              | Fast                      |
| Lazy                   | No          | Yes             | Fast                      |
| Synchronized           | Yes         | Yes             | Slower under contention   |
| Double-checked locking | Yes         | Yes             | Fast after initialization |
| Bill Pugh              | Yes         | Yes             | Fast                      |
| Enum                   | Yes         | No, effectively | Fast                      |

### How to break Singleton Pattern

A Singleton pattern can be broken when multiple instances of a class are created instead of just one.

In Java, there are **3 common** ways to break a Singleton.

**1. Reflection**

Reflection can access a private constructor and create another instance.

```java
Singleton s1 = Singleton.getInstance();

Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();

constructor.setAccessible(true);
Singleton s2 = constructor.newInstance();

System.out.println(s1 == s2); // false
```

- Why it breaks: Reflection bypasses the private constructor restriction.
- How to prevent it: Throw an exception if the constructor is invoked after the instance exists, or use an enum Singleton. Reflection defenses have limitations.

**2. Serialization and Deserialization**

Serialization saves an object, and deserialization can create a new instance when restoring it.

```java
class Singleton implements java.io.Serializable {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}
```

- Why it breaks: Deserialization can create a separate object.
- How to prevent it: Implement `readResolve()` to return the existing instance.
- Note: The class must implement `Serializable` for this example to apply.

**3. Cloning**

Cloning can create another instance if the Singleton supports `Cloneable`.

```java
class Singleton implements Cloneable {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

- Why it breaks: `clone()` creates a separate object.
- How to prevent it: Override `clone()` to return `INSTANCE` or throw `CloneNotSupportedException`.

### Advantages & Disadvantages

**Advantages**

- Single instance: Ensures only one instance of a class is created.
- Shared access: The same instance can be accessed from different parts of the application.
- Resource saving: Avoids creating multiple objects unnecessarily.
- Centralized control: Useful for managing shared resources like configuration or logging.

**Disadvantages**

- Global state: Makes the instance accessible globally, which can make code harder to understand.
- Testing difficulties: Harder to replace the instance with a mock during unit testing.
- Thread-safety issues: Poor implementation can create multiple instances in a multithreaded environment.
- Tight coupling: Classes that use the Singleton become dependent on that specific implementation.

### Conclusion

The Singleton pattern ensures that a class has only one instance and provides a common access point to it. It is useful when a single shared instance is required, but overusing it can make code harder to test, maintain, and modify. Use it only when having one instance is a genuine requirement.

<p align="right"><a href="#top">Back to top</a></p>
