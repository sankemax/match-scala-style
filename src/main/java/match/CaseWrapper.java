package main.java.match;

import java.util.function.Function;
import java.util.function.Predicate;

public class CaseWrapper<A, R> {
    private final CaseWrapperType caseWrapperType;
    private final Predicate<A> predicate;
    private final Function<A, R> mapper;

    public CaseWrapper(
            Predicate<A> predicate,
            Function<A, R> mapper,
            CaseWrapperType caseWrapperType
    ) {
        this.predicate = predicate;
        this.mapper = mapper;
        this.caseWrapperType = caseWrapperType;
    }

    public Predicate<A> getPredicate() {
        return predicate;
    }

    public Function<A, R> getMapper() {
        return mapper;
    }

    public CaseWrapperType getCaseWrapperType() {
        return caseWrapperType;
    }

    protected enum CaseWrapperType {
        Default, Case
    }
}
