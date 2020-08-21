package main.java.match;

import java.util.Optional;
import java.util.stream.Stream;

public class MatchWrapper<A> {
    private final A toMatch;

    public MatchWrapper(A toMatch) {
        this.toMatch = toMatch;
    }

    @SafeVarargs
    public final <R> Optional<R> option(CaseWrapper<A, R>... caseWrappers) {
        if (Stream
                .of(caseWrappers)
                .map(CaseWrapper::getCaseWrapperType)
                .filter(caseWrapperType -> caseWrapperType == CaseWrapper.CaseWrapperType.Default)
                .count() > 1
        ) {
            System.out.printf(
                    "[WARNING] Matcher of type [%s] on value [%s] has more than one default%n",
                    toMatch.getClass().getSimpleName(),
                    toMatch
            );
        }

        return Stream
                .of(caseWrappers)
                .dropWhile(caseWrapper -> !caseWrapper.getPredicate().test(toMatch))
                .limit(1)
                .map(caseWrapper -> caseWrapper.getMapper().apply(toMatch))
                .findFirst();
    }

    @SafeVarargs
    public final <R> R of(CaseWrapper<A, R>... caseWrappers) {
        return option(caseWrappers).get();
    }
 }
