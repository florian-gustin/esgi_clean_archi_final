package org.example.core.validation;

import org.example.core.validation.contract.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class Date implements ValueObject<LocalDate> {


    private final LocalDate value;

    public Date(String value) throws DateTimeParseException {
        this.value = LocalDate.parse(value);
    }

    @Override
    public LocalDate getValue() {
        return value;
    }
}
