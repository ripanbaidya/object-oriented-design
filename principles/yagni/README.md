# YAGNI - You Ain't Gonna Need It

The **YAGNI principle** is a core practice of Extreme Programming (XP) and agile software development. It stands for:

**"You Ain't Gonna Need It"**

It is a direct warning to developers: **Do not implement features, write code, or build architectures based on the assumption that you _might_ need them in the future.**

## The Trap of Speculative Programming

Developers are natural problem solvers, which often leads to "speculative programming"—building complex systems ahead of time because _"we will probably need this when the app scales up next year."_ YAGNI reminds us that:

1. **Requirements change rapidly.** The feature you build today for next year will likely be obsolete or incorrect by the time next year arrives.

2. **It wastes time.** Every minute spent writing code you don't need right now is time stolen from fixing bugs or polishing features you _do_ need today.

3. **It bloats the codebase.** Extra code means more unit tests to maintain, more surface area for bugs, and a steeper learning curve for new developers.

## Example

Let's look at a basic requirement: A system that needs to save a new user profile to a database.

### The Bad Way (Violating YAGNI)

This developer thinks, _"We are using a SQL database today, but we might switch to a NoSQL cloud database later, and we might need an event-logging caching mechanism, so I'll build an abstraction layer for everything right now!"_

```java
// Abstract factory created just in case we switch databases later
public interface DatabaseFactory {
    UserStorage getStorageStrategy();
}

public interface UserStorage {
    void saveUser(String username);
}

public class SqlUserStorage implements UserStorage {
    public void saveUser(String username) { System.out.println("Saved to SQL."); }
}

// The core application service is bogged down by early abstraction
public class UserService {
    private final DatabaseFactory factory;

    public UserService(DatabaseFactory factory) {
        this.factory = factory;
    }

    // Over-designed implementation for a simple feature
    public void createAccount(String username) {
        UserStorage storage = factory.getStorageStrategy();
        storage.saveUser(username);
    }
}

```

### The Good Way

Keep it dead simple. We use a SQL database today, so we write a direct SQL repository. If the infrastructure shifts two years from now, _that_ is when we will refactor and introduce interfaces.

```java
public class UserRepository {
    public void save(String username) {
        System.out.println("Saved to SQL Database.");
    }
}

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void createAccount(String username) {
        userRepository.save(username);
    }
}
```

## When to Ignore YAGNI

YAGNI is a brilliant rule of thumb, but it shouldn't be used as an excuse to write sloppy or shortsighted code. You should still plan ahead for things that are **incredibly difficult to change later**:

- **Security & Encryption:** Don't skip hashing passwords under the guise of YAGNI. Security must be built into the foundation.

- **Database Migrations:** Setting up a basic database migration tool (like Flyway or Liquibase) takes an hour on day one, but saving it for later can cause a massive headache down the road.
