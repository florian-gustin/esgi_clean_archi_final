package org.example.core.validation;

import org.example.core.validation.contract.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class SimpleDate implements ValueObject<LocalDate> {


    private final LocalDate value;

    public SimpleDate(String value) throws DateTimeParseException {
        this.value = LocalDate.parse(value);
    }

    @Override
    public LocalDate getValue() {
        return value;
    }
}
