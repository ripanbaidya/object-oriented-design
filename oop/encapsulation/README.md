# Encapsulation

Encapsulation means **wrapping data (variables) and the methods that operate on that data into a single unit (class or object)**. It also involves restricting direct access to an object's internal state and providing controlled ways to interact with it, helping maintain data integrity and preventing unintended modifications.

Encapsulation is achieved using:

- Access Modifiers (`private`, `protected`, `public`, or language-specific equivalents)
- Getters and Setters or other public methods
- Data Hiding

Encapsulation helps in **data protection, modularity, and maintainability** of the code.

**Key Benefits of Encapsulation**

- **Data Hiding**: Restricts direct access to internal data.
- **Controlled Access**: Allows validation before data is modified.
- **Improved Code Maintainability**: Enables internal implementation changes without affecting external code.
- **Better Modularity**: Organizes data and related behavior into logical units.

### Encapsulation Using Access Modifiers

Many object-oriented programming languages provide access modifiers to control visibility:

- `private`: Accessible only within the defining class.
- `protected`: Generally accessible within the defining class and its subclasses, with rules varying by language.
- `public`: Accessible from outside the class wherever permitted by the language.

Example:

```java
class BankAccount {
    
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
}
```

In this example, `balance` cannot be accessed or modified directly from outside the class. The `deposit()` method controls how the balance changes, while `getBalance()` provides read access.

**Why Use Encapsulation?**

- Prevents uncontrolled modification of internal data.
- Allows input validation and enforcement of business rules.
- Hides implementation details from external code.
- Makes code easier to maintain and modify.

### Encapsulation Using Getters and Setters

Getters and setters provide controlled ways to read and modify private data. A getter retrieves a value, while a setter updates it, potentially applying validation rules.

Example:

```java
class Employee {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        }
    }
}
```

Here, the `age` field can only be updated through a method that checks the supplied value. This helps enforce the object's rules.

Getters and setters are not mandatory for encapsulation. Providing only the operations an object needs can offer better control than exposing a setter for every field.

### Encapsulation and Data Hiding

Encapsulation groups data and behavior together, while data hiding restricts access to internal details. These concepts work together to protect an object's internal state and expose only the functionality that other parts of the program need.

**Example: Hiding Implementation Details**

```java
class Account {
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                "Initial balance cannot be negative"
            );
        }
        this.balance = initialBalance;
    }

    private boolean canWithdraw(double amount) {
        return amount > 0 && amount <= balance;
    }

    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            System.out.println("Withdrawal successful: " + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance");
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

The `canWithdraw()` method is private because it is an internal implementation detail. External code can request a withdrawal through `withdraw()` without needing to know how the validation works.

**Why Hide Internal Details?**

- Prevents uncontrolled access to important data.
- Helps preserve data consistency.
- Reduces dependencies between different parts of a program.
- Allows implementation details to change without changing the public interface.

### Encapsulation in Real-World Applications

Encapsulation is commonly used in applications such as:

- **Banking Systems** — Controlling account balances and validating transactions.
- **Healthcare Applications** — Managing access to patient information.
- **E-Commerce Platforms** — Hiding payment-processing logic and controlling transaction operations.

**Example: Encapsulation in Payment Processing**

```java
class PaymentProcessor {
    private final String maskedCardNumber;
    private final double amount;

    public PaymentProcessor(String cardNumber, double amount) {
        if (cardNumber == null || cardNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid card number");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }

        this.maskedCardNumber =
            "****-****-****-" +
            cardNumber.substring(cardNumber.length() - 4);
        this.amount = amount;
    }

    public void processPayment() {
        System.out.println(
            "Processing payment of " + amount +
            " for card " + maskedCardNumber
        );
    }
}
```

The class keeps its fields private and exposes a public operation for processing payments. Its internal data and implementation are hidden from external code.

**Why Use Encapsulation in Payment Processing?**

- Limits direct access to internal payment data.
- Hides implementation details behind a public interface.
- Allows validation before payment-related operations.
- Helps reduce accidental exposure of sensitive information.

Encapsulation alone does not guarantee security. Real payment systems also require appropriate encryption, secure storage, access controls, and compliance with applicable security standards.
