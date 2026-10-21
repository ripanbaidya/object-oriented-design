# Class Relationships

A class relationship describes **how two classes are connected, interact, or depend on each other in an object-oriented program**.

For example, in a Parking Lot system, a Car can be a type of Vehicle, a ParkingLot has ParkingSpot objects, and a PaymentService uses a PaymentGateway.

The main types of class relationships are:

- [Inheritance (Is-a)](#inheritance)
- [Association](#association)
- [Aggregation (Weak Has-a)](#aggregation)
- [Composition (Strong Has-a)](#composition)
- [Dependency](#dependency)

## Inheritance

One class is a specialized version of another class.

Example: A Car is a Vehicle.

```java
class Vehicle {
}

class Car extends Vehicle {
}
```

To learn more about `Inheritance` [click here](/oop/inheritance/README.md).

## Association

Association defines a connection between two classes, where one class is linked to another. The association can be **one-to-one**, **one-to-many**, **many-to-one**, or **many-to-many**. Objects in an association can exist independently of each other.

**Example** - A Teacher teaches a Student. Both can exist independently of each other.

```java
class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Teacher {
    String name;

    Teacher(String name) {
        this.name = name;
    }

    void teach(Student s) {
        System.out.println(name + " teaches " + s.name);
    }
}
```

Using these classes:

```java
Student s = new Student("Alice");
Teacher t = new Teacher("Mr. Smith");

t.teach(s);
```

Output:

```
Mr. Smith teaches Alice
```

**Why is this association?** The Teacher interacts with the Student, but both objects can exist independently.

### Types of Association

- One-to-One
  - One A is connected to one B.
  - Example: Person → Passport

- One-to-Many
  - One A is connected to many B objects.
  - Example: Teacher → Many Students

- Many-to-One
  - Many A objects are connected to one B.
  - Example: Many Students → One School

- Many-to-Many
  - Many A objects are connected to many B objects.
  - Example: Many Teachers ↔ Many Students

### Unidirectional vs Bidirectional

#### Unidirectional

Only one object knows about the other. Example: A Teacher knows about a Student.

```java
class Library {
    String name;

    Library(String name) {
        this.name = name;
    }
}

class Book {
    String title;
    Library library;

    Book(String title, Library library) {
        this.title = title;
        this.library = library;
    }

    void showDetails() {
        System.out.println(title + " is in " + library.name);
    }
}

public class Main {
    public static void main(String[] args) {
        Library l = new Library("City Library");

        Book b1 = new Book("1984", l);
        Book b2 = new Book("Harry Potter", l);

        b1.showDetails();
        b2.showDetails();
    }
}
```

Output:

```
1984 is in City Library
Harry Potter is in City Library
```

#### Bidirectional

Both objects know about each other. Example: A Teacher knows their students, and each Student knows their teachers.

```java
class Library {
    String name;
    List<Book> books = new ArrayList<>();

    Library(String name) {
        this.name = name;
    }

    void addBook(Book book) {
        books.add(book);
    }

    void showBooks() {
        System.out.println("Library: " + name);
        for (Book b : books) {
            System.out.println(b.title);
        }
    }
}

class Book {
    String title;
    Library library;

    Book(String title, Library library) {
        this.title = title;
        this.library = library;
        library.addBook(this);
    }

    void showLibrary() {
        System.out.println(title + " is in " + library.name);
    }
}

public class Main {
    public static void main(String[] args) {
        Library l = new Library("City Library");

        Book b1 = new Book("1984", l);
        Book b2 = new Book("Harry Potter", l);

        l.showBooks();
        b1.showLibrary();
    }
}
```

Output:

```
Library: City Library
1984
Harry Potter
1984 is in City Library
```

### How is it bidirectional?

- Book → Library: `Book` stores `Library library`.
- Library → Book: `Library` stores `List<Book> books`.

Both classes know about each other, so this is a bidirectional association.

**Characteristics of Association**

- Represents a uses-a or knows-a relationship.
- Objects in an association can exist independently.
- Can be unidirectional or bidirectional.
- Promotes modularity and code reusability.

<p align="right"><a href="#top">Back to top</a></p>

## Aggregation

Aggregation is a weak **has-a** relationship where an object of one class contains a reference to an object of another class. and the contained object can exist independently of the container. This means that even if the container object is destroyed, the contained object can still be used elsewhere in the application.

**Example** - A university has professors. Even if the university closes, the professors can still exist and work somewhere else.

```java
class Professor {
    String name;

    Professor(String name) {
        this.name = name;
    }
}

class University {
    String name;
    List<Professor> professors = new ArrayList<>();

    University(String name) {
        this.name = name;
    }

    void addProfessor(Professor p) {
        professors.add(p);
    }

    void showProfessors() {
        System.out.println(name + " professors:");

        for (Professor p : professors) {
            System.out.println(p.name);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Professor p1 = new Professor("Dr. Smith");
        Professor p2 = new Professor("Dr. Lee");

        University u = new University("MIT");

        u.addProfessor(p1);
        u.addProfessor(p2);

        u.showProfessors();
    }
}
```

Output:

```java
MIT professors:
Dr. Smith
Dr. Lee
```

**How does this show aggregation?**

- Professor objects are created independently of the University.
- The University stores references to those professors in a list.
- The professors can still be used even if the University object is no longer referenced.

The important point is that the university does not create or own the professors' lifecycles.

**Aggregation with Interfaces**

Using interfaces, we can further enhance the flexibility of aggregation.

```java
interface Teachable {
    void teach();
}

class Professor implements Teachable {
    private String name;
    private String subject;

    public Professor(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void teach() {
        System.out.println(name + " is teaching " + subject);
    }
}

class University {
    private String universityName;
    private List<Teachable> professors;

    public University(String universityName) {
        this.universityName = universityName;
        this.professors = new ArrayList<>();
    }

    public void addProfessor(Teachable professor) {
        professors.add(professor);
    }

    public void showProfessors() {
        System.out.println("Professors at " + universityName + ":");
        for (Teachable professor : professors) {
            professor.teach();
        }
    }
}

public class InterfaceAggregationExample {
    public static void main(String[] args) {
        Professor prof1 = new Professor("Dr. Adams", "Physics");
        Professor prof2 = new Professor("Dr. Lee", "Chemistry");

        University university = new University("MIT");
        university.addProfessor(prof1);
        university.addProfessor(prof2);

        university.showProfessors();
    }
}
```

**Output**

```
Professors at MIT:
Dr. Adams is teaching Physics
Dr. Lee is teaching Chemistry
```

**Characteristics of Aggregation:**

- Represents a **has-a** relationship.
- The contained object **can exist independently** of the container.
- Implemented using references (pointers) to objects.
- Promotes **loose coupling** between objects.

<p align="right"><a href="#top">Back to top</a></p>

## Composition

Composition is a strong **has-a** relationship where one class contains an instance (or instances) of another class as a field.

The contained class is often called a `component`, and the containing class is referred to as a `composite class`. This helps in building complex systems by combining simpler objects.

**Remember** - Composition is not inheritance. Inheritance means is-a, while composition means has-a.

**Example** - A House has Rooms. In a simplified model, the rooms are considered parts of the house.

```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine;

    Car() {
        engine = new Engine();
    }

    void drive() {
        engine.start();
        System.out.println("Car is moving");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}
```

Output:

```
Engine started
Car is moving
```

**How does this show composition?**

- Car contains an Engine.
- The Car creates its own Engine inside its constructor.
- The Car manages that engine as one of its components.

This is a simple example of composition.

### Why Prefer Composition Over Inheritance?

#### 1. Encapsulation and Flexibility

- Composition allows us to change the behavior of an object dynamically by replacing components at runtime.
- Inheritance makes it difficult to modify an existing class hierarchy without breaking existing code.

#### 2. Better Code Reusability

- Composition promotes reusable components. The `Engine`, `Wheel`, and `Transmission` classes can be used in multiple types of vehicles (Car, Bike, Truck) without modification.

#### 3. Avoids Inheritance Pitfalls

- Inheritance can lead to deep class hierarchies, making maintenance difficult.
- It enforces strict parent-child relationships, which can be too rigid for some designs.

#### 4. Supports Interface-Based Design

- Composition can be combined with interfaces to achieve powerful decoupling.

### Composition with Interfaces

Using interfaces with composition allows for greater flexibility and loose coupling.

```java
interface Engine {
    void start();
}

class PetrolEngine implements Engine {
    public void start() {
        System.out.println("Petrol Engine started.");
    }
}

class DieselEngine implements Engine {
    public void start() {
        System.out.println("Diesel Engine started.");
    }
}

class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void startCar() {
        engine.start();
        System.out.println("Car is ready to go!");
    }
}

public class InterfaceCompositionExample {
    public static void main(String[] args) {
        Car petrolCar = new Car(new PetrolEngine());
        petrolCar.startCar();

        Car dieselCar = new Car(new DieselEngine());
        dieselCar.startCar();
    }
}
```

Output:

```
Petrol Engine started.
Car is ready to go!
Diesel Engine started.
Car is ready to go!
```

<p align="right"><a href="#top">Back to top</a></p>

## Dependency

Dependency is a relationship between two elements where one element (the client) depends on the other element (the supplier). This means that the client element requires the supplier element to function correctly.

**Note:** A dependency is generally shown as a dashed arrow pointing from the client (dependent) at the tail to the supplier (provider) at the arrowhead.

```mermaid
    flowchart LR
    A[Client] -.-> B[Supplier]
```

**Remember**: A uses B = Dependency.

**Example**: A Car needs an Engine to start.

```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    void drive(Engine engine) {
        engine.start();
        System.out.println("Car is moving");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        Engine engine = new Engine();

        car.drive(engine);
    }
}
```

Output:

```
Engine started
Car is moving
```

**How does this work?**

- Engine provides the start() method.
- Car uses an Engine inside its drive() method.
- The Engine is passed as a method parameter.
- Therefore, Car depends on Engine to perform this task.

### There are three ways to use dependencies

#### 1. Creating an object inside a class

```java
class Car {
    Engine engine = new Engine();
}
```

Car creates its own Engine. This creates a direct dependency and makes replacing the engine harder.

#### 2. Constructor injection

```java
class Car {
    Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }
}
```

The engine is provided from outside. This makes it easier to replace or test the dependency.

#### 3. Method parameter

```java
void drive(Engine engine) {
    engine.start();
}
```

The engine is needed only when drive() is called.

### Dependency Injection

Dependency Injection (DI) means providing an object that a class needs, instead of making the class create that object itself.

For example:

```java
Engine engine = new Engine();
Car car = new Car(engine);
```

Here, the Engine is injected into the Car through its constructor.

<p align="right"><a href="#top">Back to top</a></p>
