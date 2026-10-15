# Separation of Concerns (SoC)

**Separation of Concerns (SoC)** is a core software architecture principle that states a program should be divided into distinct sections, where each section addresses a separate "concern" (a specific responsibility or piece of logic).

In short: **One component should do one thing, and do it completely.**

## The Real-World Analogy: A Restaurant

Think of how a professional restaurant operates. It doesn't have one person doing everything. Instead, the responsibilities are separated:

- **The Waiter** handles customer interaction and taking orders.
- **The Chef** focuses entirely on cooking the food.
- **The Dishwasher** is solely responsible for cleaning.

If the chef suddenly had to wash dishes while cooking, the kitchen would slow down, mistakes would happen, and the system would break. By separating their concerns, each person can focus on what they do best, and changing a chef doesn't force you to retrain your waiter.

## Why SoC is Critical in Software

Without SoC, code becomes **"spaghetti code"**—a tangled mess where database logic, business logic, and user interface code are all mashed together. Applying SoC gives you:

- **Maintainability:** When a bug occurs or a feature needs an update, you know exactly which file or module to look at without worrying about breaking unrelated code.

- **Reusability:** A module that only handles one concern (like user authentication) can easily be lifted and reused in a completely different project.

- **Testability:** It is significantly easier to write unit tests for isolated, single-purpose blocks of code than for a massive, multi-purpose monolith.

- **Team Efficiency:** Multiple developers can work on the same system simultaneously (e.g., one working on the UI, another on the database) without stepping on each other's toes.

## Example

### The Bad Way (Tightly Coupled / Mixed Concerns)

In this example, a single class handles user input, calculates a discount (business logic), and saves the user to a database.

```java
public class UserManager {

    public void processUserSignUp(String name, double cartTotal) {
        // Concern 1: Input Validation
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name!");
            return;
        }

        // Concern 2: Business Logic (Calculating Discount)
        double finalPrice = cartTotal;
        if (cartTotal > 100) {
            finalPrice = cartTotal * 0.9; // 10% off
        }
        System.out.println("Final total: " + finalPrice);

        // Concern 3: Data Storage (Simulating Database Saving)
        System.out.println("Saving user " + name + " to the database...");
    }
}

```

**Why this is bad:** If you want to change how you calculate discounts, you have to risk breaking your database logic. If you swap your database, you have to touch your business logic.

### The Good Way (Separated Concerns)

We split the responsibilities into three dedicated classes:

```java
// Concern 1: Business Logic Only
public class DiscountService {
    public double calculateDiscount(double total) {
        return total > 100 ? total * 0.9 : total;
    }
}

// Concern 2: Database Operations Only
public class UserRepository {
    public void save(String name) {
        System.out.println("Saving user " + name + " to the database...");
    }
}

// Concern 3: Orchestration / Application Flow
public class UserService {
    private DiscountService discountService = new DiscountService();
    private UserRepository userRepository = new UserRepository();

    public void registerUser(String name, double cartTotal) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        double finalPrice = discountService.calculateDiscount(cartTotal);
        userRepository.save(name);
    }
}
```

Now, each class has a single, clear job. If your database technology changes, you only modify `UserRepository`. If your discount policy changes, you only touch `DiscountService`.

## Closely Related Principles

If you want to dive deeper into this mindset, look into:

- **Single Responsibility Principle (SRP):** The "S" in SOLID design principles, which states a class should have only one reason to change.

- **Microservices:** An architectural style that takes SoC to the network level, breaking a massive application down into tiny, independent, single-purpose web services.
