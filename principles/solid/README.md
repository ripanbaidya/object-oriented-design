# SOLID Principle

The SOLID principles were introduced by Robert C. Martin in his 2000 paper “Design Principles and Design Patterns.” These concepts were later built upon by Michael Feathers, who introduced us to the SOLID acronym. And in the last 20 years, these five principles have revolutionized the world of object-oriented programming, changing the way that we write software.

So, what is SOLID and how does it help us write better code? Simply put, Martin and Feathers’ design principles encourage us to create more maintainable, understandable, and flexible software. Consequently, as our applications grow in size, we can reduce their complexity and save ourselves a lot of headaches further down the road!

The following five concepts make up our SOLID principles:

1. [Single Responsibility](#single-responsibility)
2. [Open/Closed](#open-for-extension-closed-for-modification)
3. [Liskov Substitution](#liskov-substitution)
4. [Interface Segregation](#interface-segregation)
5. [Dependency Inversion](#dependency-inversion)

While these concepts may seem daunting, they can be easily understood with some simple code examples. In the following sections, we’ll take a deep dive into these principles, with a quick Java example to illustrate each one.

## Single Responsibility

This principle states that a class should only have one responsibility. Furthermore, it should only have one reason to change.

How does this principle help us to build better software? Let’s see a few of its benefits:

- Testing – A class with one responsibility will have far fewer test cases.
- Lower coupling – Less functionality in a single class will have fewer dependencies.
- Organization – Smaller, well-organized classes are easier to search than monolithic ones.

For example, in an online shopping application, an Order class can calculate the total price of an order. If we also add email functionality to this class, it will have two responsibilities.

```java
public class Order {

    public double calculateTotal(double price, int quantity) {
        return price * quantity;
    }

    public void sendConfirmationEmail() {
        // Logic to send an email
    }
}
```

To follow SRP, we can move the email functionality into a separate class.

```java
public class Order {

    public double calculateTotal(double price, int quantity) {
        return price * quantity;
    }
}
```

```java
public class EmailService {

    public void sendConfirmationEmail() {
        // Logic to send an email
    }
}
```

Now, Order handles order calculations, while EmailService handles sending emails. If the email functionality changes, we can modify EmailService without changing the Order class.

<p align="right"><a href="#top">Back to top</a></p>

## Open for Extension, Closed for Modification

The Open-Closed Principle (OCP) is the second principle of SOLID. Simply put, **classes should be open for extension but closed for modification**.

This means we should be able to add new functionality to an application without changing existing code unnecessarily. This helps reduce the chances of introducing new bugs into working code.

Let’s understand this with an example.

Imagine we’re building an application that calculates discounts for customers. Initially, we offer a **10%** discount to regular customers.

```java
public class Discount {

    public double calculateDiscount(double price) {
        return price * 0.10;
    }
}
```

The application works fine, but after a few months, we decide to offer a **20%** discount to premium customers as well.

We might be tempted to modify the existing `Discount` class by adding `if-else` conditions for different customer types.

```java
public class Discount {

    public double calculateDiscount(String customerType, double price) {

        if (customerType.equals("REGULAR")) {
            return price * 0.10;
        }
        else if (customerType.equals("PREMIUM")) {
            return price * 0.20;
        }
        // More customer types
        else {
            return 0;
        }
    }
}
```

However, as more discount types are introduced, the class becomes harder to maintain.

Instead, let’s follow the Open-Closed Principle by creating a common interface and separate classes for each discount type.

```java
public interface Discount {
    double calculateDiscount(double price);
}
```

Now, we can create different discount classes:

```java
public class RegularDiscount implements Discount {

    @Override
    public double calculateDiscount(double price) {
        return price * 0.10;
    }
}
```

```java
public class PremiumDiscount implements Discount {

    @Override
    public double calculateDiscount(double price) {
        return price * 0.20;
    }
}
```

Now, if we want to introduce a new discount for VIP customers, we can simply create another class:

```java
public class VIPDiscount implements Discount {

    @Override
    public double calculateDiscount(double price) {
        return price * 0.30;
    }
}
```

We don’t need to modify the existing `RegularDiscount` or `PremiumDiscount` classes to add this new functionality.

By following the Open-Closed Principle, we can extend our application by adding new classes without unnecessarily modifying existing code. This makes the application easier to maintain and reduces the risk of introducing new bugs.

<p align="right"><a href="#top">Back to top</a></p>

## Liskov Substitution

The Liskov Substitution Principle (LSP) is the third principle of SOLID. Simply put, **a child class should be able to replace its parent class without breaking the behavior of the application**.

This means that when we create a child class, it should behave as expected wherever the parent class is used.

Let’s understand this with an simple example.

Imagine we’re building an application that manages different types of **birds**. We create a `Bird` class with a method called `fly()`.

```java
public class Bird {

    public void fly() {
        System.out.println("Bird is flying");
    }
}
```

Now, we create two types of birds: `Sparrow` and `Penguin`.

```java
public class Sparrow extends Bird {
    // Sparrow can fly
}
```

```java
public class Penguin extends Bird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins cannot fly!");
    }
}
```

At first, everything looks fine. However, penguins cannot fly, so we have a problem.

Imagine we have a method that makes any bird fly:

```java
public class BirdService {

    public void makeBirdFly(Bird bird) {
        bird.fly();
    }
}
```

We can use it with a sparrow:

```java
BirdService service = new BirdService();

service.makeBirdFly(new Sparrow());
```

Output:

```
Bird is flying
```

But if we pass a penguin:

```
service.makeBirdFly(new Penguin());
```

The application throws an exception.

**What is the problem?**

The `Penguin` class is a child of `Bird`, but it cannot fulfill the behavior expected from the parent class. Replacing `Bird` with `Penguin` breaks the program.

This **violates** the Liskov Substitution Principle.

### How can we fix it?

Instead of assuming every bird can fly, we should separate the general bird behavior from flying behavior.

First, create a general `Bird` class:

```java
public class Bird {
    // Common bird properties and behaviors
}
```

Next, create an interface for birds that can fly:

```java
public interface Flyable {
    void fly();
}
```

Now, implement the appropriate behavior for each bird.

```java
public class Sparrow extends Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}
```

```java
public class Penguin extends Bird {
    // Penguins cannot fly, so Flyable is not implemented
}
```

Now, our service accepts only birds that can fly:

```java
public class BirdService {

    public void makeBirdFly(Flyable bird) {
        bird.fly();
    }
}
```

Usage:

```java
BirdService service = new BirdService();

service.makeBirdFly(new Sparrow());
```

Output:

```
Sparrow is flying
```

Now, `Penguin` cannot accidentally be passed to `makeBirdFly()` because it does not implement `Flyable`.

By designing our classes this way, each class fulfills the behavior expected from the type it implements.

**Note:** A child class should honor the behavior promised by its parent class. If certain subclasses cannot fulfill that behavior, we should rethink the class hierarchy rather than forcing them to implement methods they cannot support.

<p align="right"><a href="#top">Back to top</a></p>

## Interface Segregation

The Interface Segregation Principle (ISP) is the **fourth principle** of SOLID. Simply put, **a class should not be forced to implement methods that it does not need**.

This means that instead of creating one large interface containing many unrelated methods, we should split it into smaller, more specific interfaces.

Let’s understand this with a real-life example.

Imagine we’re building software for office machines. Some machines can print, scan, and fax documents, while others can only print.

We start by creating a single interface:

```java
public interface OfficeMachine {

    void print();

    void scan();

    void fax();
}
```

Now, we create a simple printer that can only print documents.

```java
public class SimplePrinter implements OfficeMachine {

    @Override
    public void print() {
        System.out.println("Printing document");
    }

    @Override
    public void scan() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fax() {
        throw new UnsupportedOperationException();
    }
}
```

As you can see, `SimplePrinter` is forced to implement `scan()` and `fax()`, even though it doesn't support these features.

This makes our design unnecessarily complicated and **violates the Interface Segregation** Principle.

### How can we fix it?

Instead of using one large interface, we can split it into smaller interfaces based on functionality.

```java
public interface Printer {
    void print();
}
```

```java
public interface Scanner {
    void scan();
}
```

```java
public interface FaxMachine {
    void fax();
}
```

Now, each machine can implement only the interfaces it needs.

A simple printer:

```java
public class SimplePrinter implements Printer {

    @Override
    public void print() {
        System.out.println("Printing document");
    }
}
```

An all-in-one office machine:

```java
public class AllInOneMachine  implements Printer, Scanner, FaxMachine {

    @Override
    public void print() {
        System.out.println("Printing document");
    }

    @Override
    public void scan() {
        System.out.println("Scanning document");
    }

    @Override
    public void fax() {
        System.out.println("Sending fax");
    }
}
```

- `SimplePrinter` only implements the `Printer` interface.
- `AllInOneMachine` implements all three interfaces because it supports all three features.
- We no longer force classes to implement methods they don't need.

By following the Interface Segregation Principle, our code becomes easier to maintain, extend, and test.

**Note:** Keep interfaces small and focused so that each class only depends on the functionality it actually needs.

<p align="right"><a href="#top">Back to top</a></p>

## Dependency Inversion

The Dependency Inversion Principle (DIP) is the **fifth principle** of SOLID. Simply put, **high-level classes should not depend directly on low-level classes. Both should depend on abstractions, such as interfaces**.

This means that instead of a class creating and depending on a specific implementation, it should depend on an interface. This makes it easier to replace implementations without changing the class that uses them.

Let’s understand this with a real-life example.

Imagine we’re building an application that sends notifications to users. Initially, we only support **email** notifications.

We create an `EmailService` class:

```java
public class EmailService {

    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}
```

Now, we create a `NotificationService` that uses `EmailService` to send notifications.

```java
public class NotificationService {

    private EmailService emailService = new EmailService();

    public void notifyUser(String message) {
        emailService.sendEmail(message);
    }
}
```

This works fine. However, after some time, the business wants to support **SMS notifications** as well.

We create an `SmsService`:

```java
public class SmsService {

    public void sendSms(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
```

**What is the problem?**

Our `NotificationService` is tightly coupled to `EmailService`. If we want to switch to **SMS**, we must modify the `NotificationService` class.

As more notification types are introduced, the class becomes harder to maintain.

**How can we fix it?**

Instead of making `NotificationService` depend directly on a specific implementation, we can introduce an interface called `Notification`.

Step 1: Create an interface

```java
public interface Notification {

    void send(String message);
}
```

Step 2: Implement the interface for email

```java
public class EmailService implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}
```

Step 3: Implement the interface for SMS

```java
public class SmsService implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
```

Step 4: Update the notification service

```java
public class NotificationService {

    private final Notification notification;

    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public void notifyUser(String message) {
        notification.send(message);
    }
}
```

Notice that `NotificationService` depends on the `Notification` interface rather than any spefic types like - `EmailService` or `SmsService`.

Step 5: Use the notification service

```java
Notification email = new EmailService();
Notification sms = new SmsService();

NotificationService emailService = new NotificationService(email);
NotificationService smsService = new NotificationService(sms);

emailService.notifyUser("Your order has been shipped");
smsService.notifyUser("Your order has been delivered");
```

Output:

```
Sending email: Your order has been shipped
Sending SMS: Your order has been delivered
```

**What have we achieved?**

- `NotificationService` no longer depends directly on `EmailService` or `SmsService`.
- Both implementations depend on the `Notification` interface.
- We can add new notification types, such as WhatsApp, by creating another implementation without changing `NotificationService`.
- We can easily replace implementations during testing.

Passing the dependency through the constructor, as shown above, is called **constructor dependency injection**.

By following the Dependency Inversion Principle, our classes become less tightly coupled and easier to maintain and test.

Depend on abstractions rather than concrete implementations. This allows you to change or add implementations without unnecessarily changing the classes that use them.

<p align="right"><a href="#top">Back to top</a></p>

## Conclusion

In this article, we explored the five SOLID principles of object-oriented design and understood how they help us write better code.

We learned how to make classes more focused, extend functionality without modifying existing code unnecessarily, ensure subclasses behave as expected, keep interfaces small, and reduce dependencies between classes.

By following these principles, we can make our code easier to maintain, test, extend, and understand. Although SOLID may seem complicated at first, applying these principles through practical examples will help us design better software and build applications that are easier to adapt to changing requirements.

<p align="right"><a href="#top">Back to top</a></p>

## References

- https://www.baeldung.com/solid-principles
