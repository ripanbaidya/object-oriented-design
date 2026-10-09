# Introduction to Low-Level Design (LLD)

<p align="right">Last updated - 09.10.2026</p>

## What is Low-Level Design?

In software engineering interviews, system design is generally split into two phases: **High-Level Design (HLD)** and **Low-Level Design (LLD)**.

While **HLD** focuses on system architecture, large-scale components, and high-level data flow, **LLD** zooms in on the internal details of individual components.

- **HLD** asks: "What components do we need, and how do they communicate?"
- **LLD** asks: "How do we implement each specific component in code?"

### Definition

**Low-Level Design (LLD)** is the process of converting architectural components into modular, executable code structures. It bridges the gap between high-level architecture and actual software implementation.

LLD defines the granular details of a codebase, including classes, interfaces, attributes, methods, design patterns, and entity relationships.

**Example:**

- **HLD view:** "The system requires a Notification Service."
- **LLD view:** "We will define a `NotificationSender` interface implemented by `EmailSender`, `SmsSender`, and `PushNotificationSender`, managed by a `NotificationManager` class."

## Core Components of LLD

### 1. Classes and Objects

Classes define the primary entities, state, and behavior of a system.

- **Responsibilities:** The single role or function assigned to a class.
- **Attributes (State):** The properties or data the class holds (e.g., `id`, `name`).
- **Methods (Behavior):** The operational logic the class performs (e.g., `save()`, `validate()`).

_Example:_ In a food delivery platform, classes include `User`, `Restaurant`, `Order`, and `Payment`. A `User` class contains:

- **Attributes:** `userId`, `email`, `phoneNumber`
- **Methods:** `browseMenu()`, `placeOrder()`, `trackStatus()`

### 2. Interfaces and Abstraction

Interfaces establish contracts between system components. They enforce loose coupling, allowing modules to interact without relying on internal implementation details.

### 3. Class Relationships

Classes interact through specific relational structures:

- **Association:** A general usage relationship (e.g., a `Doctor` uses a `Stethoscope`).
- **Aggregation (Weak Has-A):** A container-contained relationship where child entities can exist independently of the parent (e.g., a `Department` has `Professors`; if the department closes, the professors still exist).
- **Composition (Strong Has-A):** A strict ownership relationship where child entity lifecycles depend entirely on the parent (e.g., a `House` is composed of `Rooms`; destroying the house destroys the rooms).
- **Inheritance (Is-A):** A class derives state and behavior from a parent class (e.g., a `Car` is a `Vehicle`).

#### Cardinality

Cardinality defines the numerical relationships between instances:

- **One-to-One (1:1):** One instance relates to exactly one other instance (e.g., a `User` has one `Profile`).
- **One-to-Many (1:N):** One instance relates to multiple instances (e.g., a `Customer` has multiple `Orders`).
- **Many-to-Many (M:N):** Multiple instances relate to multiple instances (e.g., a `Student` registers for multiple `Courses`, and a `Course` contains multiple `Students`).

### 4. Method Signatures

Method signatures define class behaviors clearly. A well-designed method signature includes:

- Scope modifiers (`public`, `private`, `protected`).
- Clear input parameter names and types.
- Specific return types.
- Declared exceptions.
- Synchronous or asynchronous execution flow.

_Poor:_ `void sendMsg(String str)`

_Better:_ `void sendNotification(Message message)`

### 5. Design Patterns

Design patterns provide reusable, battle-tested solutions to common software design problems. They help create maintainable, flexible codebases.

### 6. Data Structures

Data structures determine how data is stored, organized, and retrieved efficiently within the module (e.g., arrays, maps, stacks, queues, trees, or graphs).

## Importance of LLD in Software Development

A strong low-level design ensures that architectural requirements translate cleanly into scalable software.

- **Scalability and Performance:** Supports system growth without requiring major structural rewrites.
- **Maintainability:** Clear code structures simplify debugging, refactoring, and feature updates.
- **Testability:** Modular design enables isolated unit testing.
- **Collaboration:** Clear component boundaries allow multiple engineers to work simultaneously without code conflicts.
- **Reusability:** Independent components can be reused across different modules or applications.
- **Cost Efficiency:** Reduces long-term technical debt and bug fix costs.

## Importance of LLD in Technical Interviews

LLD interview performance demonstrates practical engineering capability beyond basic algorithmic problem-solving.

1. **Problem Breakdown:** Ability to translate ambiguous requirements into clean class structures.
2. **Object-Oriented Design (OOD):** Application of abstraction, encapsulation, inheritance, and polymorphism.
3. **SOLID Principles and Design Patterns:** Applying software principles to build extendable code.
4. **Data Structure Choice:** Selecting optimal data structures for internal class storage and retrieval.
5. **Clean Code:** Writing readable, maintainable, and well-structured code.
6. **Trade-off Analysis:** Evaluating and justifying design choices based on constraints.

## High-Level Design (HLD) vs. Low-Level Design (LLD)

Building complex software is similar to construction:

- **HLD** is the master city plan: deciding infrastructure zones, road networks, and utility grids.
- **LLD** is the architectural blueprint for an individual building: defining floor plans, electrical wiring, and plumbing layouts.

```
+-------------------------------------------------+
|              System Requirements                |
+-------------------------------------------------+
                        |
                        v
+-------------------------------------------------+
|            High-Level Design (HLD)              |
| - Microservices architecture                    |
| - Database Selection (SQL/NoSQL)                |
| - Communication Protocols (REST, gRPC, Queues)  |
+-------------------------------------------------+
                        |
                        v
+-------------------------------------------------+
|            Low-Level Design (LLD)               |
| - Class Diagrams & Interfaces                   |
| - Design Patterns & Relationships               |
| - Method Signatures & Data Structures           |
+-------------------------------------------------+
                        |
                        v
+-------------------------------------------------+
|               Implementation                    |
| - Working Code                                  |
| - Unit Tests                                    |
+-------------------------------------------------+

```

### High-Level Design (HLD)

HLD defines overall architecture and module interactions.

- Focuses on major system boundaries, microservices, and external integrations.
- Key decisions: database types, caching layers, load balancers, and communication protocols (REST, gRPC, message queues).
- _Example (Ride-Hailing App):_ Outlining Passenger Service, Driver Service, Matching Service, and Billing Service, along with how they interact using message queues and API gateways.

### Low-Level Design (LLD)

LLD focuses on the internal structure of a single service or component.

- Defines specific classes, interfaces, method signatures, exceptions, and data models.
- _Example (Ride-Hailing Billing Service):_ Defining a `BillingService` using a `PaymentStrategy` interface, implemented by `CreditCardPayment` and `WalletPayment` classes.

## Common LLD Interview Formats

Different organizations structure LLD interviews based on their engineering needs:

### 1. Object-Oriented Design (OOD)

- **Focus:** Modeling entities, interfaces, class hierarchies, and relationships.
- **Format:** Whiteboard or shared text document; runnable code is usually not required.
- **Common at:** Large tech enterprises (Google, Amazon, Meta, Microsoft).

### 2. Machine Coding

- **Focus:** Writing fully functional, executable, and modular code under time constraints.
- **Format:** IDE-based implementation including unit tests, edge case handling, and error logging.
- **Common at:** Startups and product-focused engineering firms.

### 3. Concurrency Design

- **Focus:** Thread-safety, synchronization, shared resources, race conditions, and deadlocks.
- **Format:** Writing thread-safe implementations (e.g., designing an in-memory thread-safe cache or task scheduler).

### 4. API Design

- **Focus:** Contract design, interface definitions, method parameters, error handling, and response objects.
- **Format:** Writing class interfaces or REST/gRPC contracts without deep underlying logic.

### 5. Schema Design

- **Focus:** Relational database modeling, normalization, table relationships, foreign key constraints, and indexing strategies.
- **Format:** Defining database schemas for specific application modules.

## Conclusion

Low-Level Design translates architectural plans into reliable, maintainable code. Mastering LLD requires understanding object-oriented programming principles, applying solid structural relationships, choosing appropriate data structures, and using design patterns effectively.

## References

