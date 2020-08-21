package main.java.examples;

import main.java.match.BaseMatch;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static main.java.match.BaseMatch.*;

public class Main {
    public static void main(String[] args) {
        var whatIsThisMagic = BaseMatch.Match(10).option(
                Case(x -> x > 10, x -> x + " is larger than 10"),
                Case(x -> x < 10, x -> x + " is smaller than 10"),
                Case($(10), x -> x + " is 10"),
                Default(x -> x + " is stranger than we thought")
        );

        var unsafeMagic = Match(LocalDate.now()).of(
                Case(instanceOf(Date.class), "its a local date"),
                Case(date -> date.isAfter(LocalDate.now()), "its later then now"),
                Case(date -> date.isBefore(LocalDate.now()), "its before now"),
                Case($(LocalDate.now()), "its today"),
                Default("somethings wrong")
        );

        var a = Match(Optional.of(5)).of(
                Case($Optional($(4)), "this is 4"),
                Case($Optional($(5)), "this is 5"),
                Default(x -> x + "")
        );

        System.out.printf("[%s]%n", a);
    }
}
