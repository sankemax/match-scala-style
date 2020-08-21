package main.java.match;

import java.lang.reflect.Method;
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

    public static <A, R> Predicate<R> $d(Predicate<A> predicate) {
        return (R toMatch) -> {
            try {
                Method aGetMethod = toMatch.getClass().getMethod("get");
                A innerValue = (A) aGetMethod.invoke(toMatch);
                return predicate.test(innerValue);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
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
