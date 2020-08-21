package main.java.run;

import main.java.match.BaseMatch;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        var whatIsThisMagic = BaseMatch.Match(10).option(
                BaseMatch.Case(x -> x > 10, x -> x + " is larger than 10"),
                BaseMatch.Case(x -> x < 10, x -> x + " is smaller than 10"),
                BaseMatch.Case(BaseMatch.$(10), x -> x + " is 10"),
                BaseMatch.Default(x -> x + " is stranger than we thought")
        );

        var unsafeMagic = BaseMatch.Match(LocalDate.now()).of(
                BaseMatch.Case(BaseMatch.instanceOf(Date.class), "its a local date"),
                BaseMatch.Case(date -> date.isAfter(LocalDate.now()), "its later then now"),
                BaseMatch.Case(date -> date.isBefore(LocalDate.now()), "its before now"),
                BaseMatch.Case(BaseMatch.$(LocalDate.now()), "its today"),
                BaseMatch.Default("somethings wrong")
        );

        var a = BaseMatch.Match(Optional.of(5)).of(
                BaseMatch.Case(BaseMatch.$Optional(BaseMatch.$(4)), "this is 4"),
                BaseMatch.Case(BaseMatch.$Optional(BaseMatch.$(5)), "this is 5"),
                BaseMatch.Default(x -> x + "")
        );

        System.out.printf("[%s]%n", a);
    }
}
