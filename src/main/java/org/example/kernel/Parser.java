package org.example.kernel;

import org.example.application.TaskParams;

public interface Parser<T> {

    public UseCaseParams parse(T value);
}
