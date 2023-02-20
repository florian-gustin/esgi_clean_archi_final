package org.example.infrastructure.io.logger;

import org.example.infrastructure.io.logger.DebugLevel;

public interface Logger<T> {
    T message(String value);
    T message(String value, DebugLevel level);
}
