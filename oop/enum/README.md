# Enums

An **enum (enumeration)** is a special type in Java used to define a fixed set of named constants.

Use an enum when a variable should hold only one value from a predefined set of options.

For example, an order can have different statuses: `PENDING`, `SHIPPED`, `DELIVERED`, or `CANCELLED`.

```java
public enum OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
```

Here, `OrderStatus` defines four valid constants. A variable of this type can refer to any of these constants, but it cannot be assigned an arbitrary string or integer.

### How to use an enum

```java
OrderStatus status = OrderStatus.SHIPPED;

if (status == OrderStatus.SHIPPED) {
    System.out.println("Order has been shipped");
}
```

**Notes:**

- `enum` is the keyword used to declare an enum.
- `OrderStatus.SHIPPED` accesses a specific enum constant.
- Use `==` to compare enum constants.
- Enums make code easier to understand and help prevent invalid assignments.

## Why Do We Use Enums

Consider representing order status using strings:

```java
String status = "SHIPPEDD"; // Typo
```

The compiler will not reject this typo because it is a valid string. It may cause problems when the application processes the status.

Using an enum:

```java
OrderStatus status = OrderStatus.SHIPPED;
```

Now, Java checks that the assigned value belongs to the `OrderStatus` type.

The main advantages are:

- **Type safety:** Prevents assigning unrelated values to an enum variable.
- **Readability:** `OrderStatus.SHIPPED` is more meaningful than an unexplained number such as `2`.
- **Maintainability:** A fixed set of values is defined in one place.
- **IDE support:** Autocomplete and compiler checks help catch mistakes.

## Enums with Fields, Constructors, and Methods

Enums can contain fields, constructors, and methods, just like classes. This is useful when each constant needs to store additional information.

For example, consider coins and their values.

```java
public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

Usage:

```java
Coin coin = Coin.DIME;

System.out.println(coin);            // DIME
System.out.println(coin.getValue()); // 10
```

**How does it work?**

1. `PENNY(1)` passes `1` to the enum constructor.
2. `Coin(int value)` receives the value.
3. `this.value = value` stores it in the instance field.
4. `getValue()` returns the value associated with that constant.

**Important points:**

- Enum constructors cannot be `public` or `protected`.
- Enum constants are created by Java; you cannot instantiate an enum using `new`.
- Fields and methods are optional. Use them only when constants need associated data or behavior.

## Built-in Enum Methods

Java provides useful methods for working with enums.

**values()** - Returns an array containing all enum constants in their declaration order.

```java
for (OrderStatus status : OrderStatus.values()) {
    System.out.println(status);
}
```

**valueOf()** - Returns the enum constant matching the given name exactly.

```java
OrderStatus status = OrderStatus.valueOf("SHIPPED");
```

If the name does not match an existing constant, Java throws `IllegalArgumentException`.

**ordinal()** - Returns the zero-based position of a constant in its declaration order.

```java
System.out.println(OrderStatus.SHIPPED.ordinal());
```

If `SHIPPED` is the second constant, the result is `1`.

**Note:** Avoid using `ordinal()` to represent business values or persist enum positions. Reordering the constants can change the result. Use an explicit field instead when a stable value is needed.

## Where Should You Use Enums

Enums are appropriate when the set of possible values is known and limited.

| Use case     | Example                           |
| ------------ | --------------------------------- |
| Order status | `PENDING`, `SHIPPED`, `DELIVERED` |
| User roles   | `ADMIN`, `CUSTOMER`, `DRIVER`     |

If values need to be created dynamically and are not restricted to a fixed set, an enum may not be the right choice.

## Important Rules

1. **An enum is a reference type**, not a primitive type.
2. Every Java enum implicitly extends `java.lang.Enum`. Therefore, an enum cannot extend another class.
3. An enum can implement interfaces.
4. Enum constants are singleton instances of their respective enum constants.
5. Enum constructors cannot be `public` or `protected`.
6. You can define fields, methods, and constructors inside an enum.
7. You can compare enum constants using `==`.
8. If you declare a constructor, fields, or methods, place a semicolon after the list of enum constants before the additional declarations.

## Conclusion

The most important concept is that **an enum represents a fixed set of valid choices as a separate type**. Understand how to declare and use enums, why they provide type safety, how constructors and fields work, and the purpose of `values()` and `valueOf()`. These are the fundamentals to know before moving on to interfaces.
