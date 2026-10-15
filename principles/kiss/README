# KISS - Keep It Simple, Stupid

The **KISS principle** is a design rule that originated in the U.S. Navy in 1960. While it has a few variations, the most popular acronym stands for:

**"Keep It Simple, Stupid"** (or more gently, **"Keep It Short and Simple"**).

In software engineering, the principle dictates that systems work best if they are kept simple rather than made complex. Therefore, simplicity should be a key goal in design, and unnecessary complexity should be avoided at all costs.

## The Reality of Software

Developers often suffer from a condition known as **"Over-engineering."** It is tempting to write clever, flashy code or build highly complex architectures to handle hypothetical problems that might happen 5 years from now.

KISS tells us to focus exclusively on the problem at hand today. Clever code is notoriously hard to read, painful to debug, and brutal for another developer to modify.

> Simple code is not "dumb" code. Writing simple, elegant solutions to complex problems requires a much higher level of skill than writing a convoluted, overly complex mess.

## Example

Let's look at a basic requirement: Checking if a number is even.

### The Bad Way

```java
public boolean checkEvenNumber(int number) {
    if (number % 2 == 0) {
        return true;
    } else {
        return false;
    }
}
```

### The Good Way

```java
public boolean isEven(int number) {
    return number % 2 == 0;
}
```

Both work, but the second is simpler and easier to read.

## How to Apply

- Write code that is easy to understand.
- Avoid unnecessary abstractions and complexity.
- Use existing libraries instead of reinventing the wheel.
- Solve the current problem instead of preparing for hypothetical requirements.
- Break down methods when they become difficult to understand.

## Benefits

- **Faster Debugging:** When something goes wrong, simple code allows you to locate the issue almost instantly.
- **Seamless Onboarding:** New developers joining a project can understand the code and start contributing immediately without needing a massive architecture guide.
- **Better Maintainability:** Code is written once but read thousands of times. Simple code significantly drops the lifetime cost of software development.
