package org.example.infrastructure.adapter;

public interface Adapter<T> {
    T convertToObject(String value);
    String convertToString(T object);

}
