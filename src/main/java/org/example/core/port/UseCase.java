package org.example.core.port;

public interface UseCase<I, O> {

    O apply(I input);
}
