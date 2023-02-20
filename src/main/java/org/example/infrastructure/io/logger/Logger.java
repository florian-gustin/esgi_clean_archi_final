package org.example.infrastructure.io.logger;

public interface Logger<T> {
    T message(String value);
    T message(String value, DebugLevel level);
}
