package org.example.domain.interfaces;

public interface Adapter<T> {
    T convertToObject(String value);
    String convertToString(T object);

}
