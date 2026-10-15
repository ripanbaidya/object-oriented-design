# DRY - Don’t Repeat Yourself

The **DRY (Don't Repeat Yourself)** principle is one of the most fundamental rules of software development. Formulated by _Andy Hunt_ and _Dave Thomas_ in their book _The Pragmatic Programmer_, its core definition is:

> "Every piece of knowledge must have a single, unambiguous, authoritative representation within a system."

In simple terms: **Avoid duplicating code, logic, or data.** If you find yourself copying and pasting the same block of code in multiple places, you are **violating the DRY principle**.

## The Core Concept: Duplication is the Enemy

When you repeat code, you create a maintenance nightmare. Imagine you have the exact same 10 lines of tax calculation logic written in four different files. If the tax laws change tomorrow, you have to find and modify all four places.

- If you miss one, you introduce a critical bug.
- If you make a typo in just one of them, your system becomes inconsistent.

By applying DRY, you extract that logic into a single method. If the law changes, you update it in **one place**, and the entire application updates instantly.

## Example

Let's look at an e-commerce checkout system processing different types of orders.

### The Bad Way

```java
public class OrderProcessor {

    public void processStandardOrder(String customerName, double price) {
        // Duplicated Validation
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid customer name");
        }

        // Duplicated Formatting
        String formattedMessage = String.format("Processing order for %s. Total: $%.2f", customerName, price);

        System.out.println(formattedMessage);
        System.out.println("Shipping physical item...");
    }

    public void processDigitalOrder(String customerName, double price) {
        // Duplicated Validation
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid customer name");
        }

        // Duplicated Formatting
        String formattedMessage = String.format("Processing order for %s. Total: $%.2f", customerName, price);

        System.out.println(formattedMessage);
        System.out.println("Emailing digital download link...");
    }
}
```

### The Good Way

```java
public class OrderProcessor {

    public void processStandardOrder(String customerName, double price) {
        logOrder(customerName, price);
        System.out.println("Shipping physical item...");
    }

    public void processDigitalOrder(String customerName, double price) {
        logOrder(customerName, price);
        System.out.println("Emailing digital download link...");
    }

    // Single authoritative location for validation and logging
    private void logOrder(String customerName, double price) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid customer name");
        }
        String formattedMessage = String.format("Processing order for %s. Total: $%.2f", customerName, price);
        System.out.println(formattedMessage);
    }
}
```

## The Golden Nuance: Don't Over-DRY Your Code!

While DRY is a powerful principle, beginners often take it too far. Software veterans have a warning: **"Abstraction is cheap, but the wrong abstraction is incredibly expensive."**

You should only eliminate duplication if the code represents the **same fundamental knowledge or business rule**. If two chunks of code look identical today purely by coincidence, but they represent entirely different business concepts, **do not merge them.**

**Example:** A method that validates a user's age might look exactly like a method that validates the quantity of items in a shopping cart (both check `if (value < 0)`). They look identical, but they are entirely separate concepts. Forcing them into a single generic function will make your code rigid and highly confusing when age laws change but cart limits stay the same.

## Key Benefits

- **Maintainability:** Fix a bug or change a requirement in one location, and it cascades smoothly throughout the application.
- **Readability:** Codebases are significantly smaller and cleaner, making it easier for new developers to understand the architecture.
- **Testing Efficiency:** You only need to write robust unit tests for the single, extracted component rather than testing identical behavior across dozens of different files.
