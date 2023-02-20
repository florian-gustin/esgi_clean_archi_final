package org.example.infrastructure.io.parser;

import java.util.List;

public interface Parser<T> {

    T parse(List<String> value);
}
