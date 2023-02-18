package org.example.kernel;

public interface UseCase<I, O> {

    O apply(I input);
}
