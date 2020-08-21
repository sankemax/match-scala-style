This library is a playful attempt to replicate VAVR's functionality: 
 
```java
Match(10).option(
    Case(x -> x > 10, x -> x + " is larger than 10"),
    Case(x -> x < 10, x -> x + " is smaller than 10"),
    Case($(10), x -> x + " is 10"),
    Default(x -> x + " is stranger than we thought")
);
```

* The first parameter of `Case` gets a predicate to match the match value with.
* The second parameter of `Case` has two options: a literal value, or a mapper for the match value.
* `Default` case works like Scalas `case _`.
* `$` sign is a utility function that creates a predicate which matches the match value to a given literal.

```java
Match(LocalDate.now()).of(
    Case(date -> date.isAfter(LocalDate.now()), "its later then now"),
    Case(date -> date.isBefore(LocalDate.now()), "its before now"),
    Case(instanceOf(Date.class), "its a local date"),
    Case($(LocalDate.now()), "its today"),
    Default("somethings wrong")
    Default("somethings wrong 2")
);
```

* `instanceOf` is a built-in utility function that creates a predicate.
* When two `Default` cases will be written, a warning will be printed to `stdout`.

```java
Match(Optional.of(5)).of(
    Case($Optional($(4)), "this is 4"),
    Case($Optional($(5)), "this is 5"),
    Default(x -> x + "")
);
```

* `$Optional` is an attempt to present a specific destructuring possibility.
