package main.java.match;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseMatch {

    public static <A> MatchWrapper<A> Match(A toMatch) {
        return new MatchWrapper<>(toMatch);
    }

    public static <A> Predicate<A> $(A toMatch) {
        return toMatch::equals;
    }

    public static <A, T> Predicate<A> instanceOf(Class<T> toMatch) {
        return toMatch::isInstance;
    }

    public static <A, R> Predicate<A> $Optional(Predicate<R> predicate) {
        return (A toMatch) -> instanceOf(Optional.class).test(toMatch)
                && predicate.test(((Optional<R>) toMatch).get());
    }

    public static <A, R> CaseWrapper<A, R> Case(Predicate<A> predicate, Function<A, R> mapper) {
        return new CaseWrapper<>(predicate, mapper, CaseWrapper.CaseWrapperType.Case);
    }

    public static <A, R> CaseWrapper<A, R> Case(Predicate<A> predicate, R value) {
        return new CaseWrapper<>(predicate, (a) -> value, CaseWrapper.CaseWrapperType.Case);
    }

    public static <A, R> CaseWrapper<A, R> Default(Function<A, R> mapper) {
        return new CaseWrapper<>(any -> true, mapper, CaseWrapper.CaseWrapperType.Default);
    }

    public static <A, R> CaseWrapper<A, R> Default(R value) {
        return new CaseWrapper<>(any -> true, (a) -> value, CaseWrapper.CaseWrapperType.Default);
    }
}
