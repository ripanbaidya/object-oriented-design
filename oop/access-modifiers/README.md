# Access Modifiers

Access modifiers in Java control who can access classes, methods, variables, and constructors.

They are important for encapsulation and information hiding in Low-Level Design (LLD). They help protect an object's internal data and prevent unnecessary dependencies between classes.

## Key Rules

- Top-level classes: Can use only `public` or default (package-private) access.
- Class members: Methods, fields, and constructors can use all four access levels: `public`, `protected`, `default`, and `private`.

### Public

Scope: Accessible from any package, provided the class and its members are accessible.

Use case: Use `public` for APIs and methods that other classes need to access.

Example:

```java
package utility;

public class PublicExample {

    public void display() {
        System.out.println("Accessible globally.");
    }
}
```

Another class in a different package can access it:

```java
package main;

import utility.PublicExample;

public class Main {
    public static void main(String[] args) {

        PublicExample example = new PublicExample();
        example.display(); // Works
    }
}
```

### Private

Scope: Accessible only within the declaring class. Other classes, including subclasses, cannot directly access private members.

Use case: Use `private` to protect internal data and hide helper methods. Provide public methods only when external access is necessary.

Example:

```java
package utility;

public class PrivateExample {

    private void display() {
        System.out.println("Accessible only within PrivateExample.");
    }
}
```

Calling `example.display()` from another class causes a **compile-time error** because the method is private.

### Protected

Scope: Accessible within the same package and by subclasses in other packages, subject to Java's protected-access rules.

Use case: Use `protected` when subclasses need access to specific methods or fields to extend a class.

Important rule: When a subclass in another package accesses a protected instance member, it must do so through an appropriate reference to the subclass or its subtype, not through an arbitrary reference to the parent class.

Example:

```java
package utility;

public class ProtectedExample {

    protected void display() {
        System.out.println("Accessible within the package or through subclassing.");
    }
}
```

```java
package main;

import utility.ProtectedExample;

public class Main extends ProtectedExample {
    public static void main(String[] args) {

        Main subclassRef = new Main();
        subclassRef.display(); // Works

        ProtectedExample parentRef = new ProtectedExample();
        // parentRef.display(); // Compile-time error
    }
}
```

Here, `Main` can call `display()` through its own instance because it extends `ProtectedExample`. However, it cannot call the method through an arbitrary `ProtectedExample` reference from another package.

### Default (Package-Private)

Scope: When no access modifier is specified, the member is accessible only within the same package.

Use case: Use default access for classes and methods that should be shared within a package but hidden from other packages.

Example:

```java
package utility;

public class DefaultExample {

    void display() {
        System.out.println("Accessible only within the utility package.");
    }
}
```

A class in the `main` package can import and create a `DefaultExample` object because the class is public. However, it cannot call `display()` because the method has package-private access.

## Visibility Matrix

| Modifier    | Same Class | Same Package | Subclass in Different Package    | Other Packages                                           |
| ----------- | ---------- | ------------ | -------------------------------- | -------------------------------------------------------- |
| `public`    | Yes        | Yes          | Yes                              | Yes                                                      |
| `protected` | Yes        | Yes          | Yes, under subclass-access rules | No, unless accessed through an eligible subclass context |
| Default     | Yes        | Yes          | No                               | No                                                       |
| `private`   | Yes        | No           | No                               | No                                                       |

## Summary

- `public`: Accessible wherever the declaring type and member are accessible.
- `protected`: Accessible within the same package and through valid subclass access across packages.
- Default: Accessible only within the same package.
- `private`: Accessible only within the declaring class.

Best practice: Keep fields and implementation details private by default, and expose only what other classes actually need. This makes Java code easier to maintain, test, and extend.

## Reference

- https://www.baeldung.com/java-access-modifiers?utm_source=chatgpt.com
