# Design Patterns

Design patterns are reusable solutions to common problems in software design. They provide a proven way to organize code so it becomes easier to understand, maintain, and reuse.

Think of design patterns as **blueprints** for writing better software. Instead of solving the same coding problem from scratch every time, you can follow a commonly used approach.

**Example**

Suppose you are developing a **shopping application**. You need to create a payment system that supports UPI, credit cards, and PayPal.

- Without a design pattern, you might write lots of `if-else` statements to handle each payment type.

- With a design pattern, you can use the Strategy Pattern to define a common payment interface and implement each payment method separately. which makes adding a new payment method easier.

### Classifications of patterns

Design patterns are commonly grouped into **three categories**.

There are 23 design patterns in the GoF (Gang of Four) catalog, but you don't need to learn all of them before you start solving LLD problems.

Focus on the patterns that help you solve Low-Level Design (LLD) problems, write extensible code, and explain your design decisions clearly.

1\. **Creational Patterns**: These patterns help create objects in a flexible and controlled way.

- [Singleton](/patterns/creational/README.md#singleton-pattern) - Learn deeply
- Factory Method - Learn deeply
- Abstract Factory - Understand
- Builder - Learn deeply
- Prototype - Low priority

**Note**: Factory Method, Builder, and Singleton deserve the most attention. Understand Abstract Factory, and leave Prototype for later.

2\. **Structural Patterns**: These patterns help organize classes and objects into larger structures.

- Adapter - Learn deeply
- Decorator - Learn deeply
- Proxy - Learn deeply
- Facade - Understand
- Composite — Understand
- Bridge — Low priority
- Flyweight — Low priority

**Note**: Adapter, Decorator, and Proxy are the three to implement and compare. Know Facade and Composite well enough to recognize them in LLD problems.

3\. **Behavioral Patterns**: These patterns define how objects communicate and share responsibilities.

- Strategy - Learn deeply
- Observer - Learn deeply
- State - Learn deeply
- Chain of Responsibility - Learn deeply
- Command - Learn deeply
- Iterator - Understand
- Template Method — Understand
- Mediator — Understand
- Memento — Low priority
- Visitor — Low priority
- Interpreter — Low priority

**Note**: Strategy, Observer, State, Chain of Responsibility, and Command are the core set. They are especially useful in LLD exercises involving payments, notifications, workflows, and extensible business logic.
