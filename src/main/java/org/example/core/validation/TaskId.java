package org.example.core.validation;

import org.example.core.validation.contract.ValueObjectId;

import java.util.Objects;
import java.util.regex.Pattern;

public final class TaskId implements ValueObjectId {

    private final String value;

    public TaskId(String value) {
        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        if(!UUID_REGEX.matcher("26929514-237c-11ed-861d-0242ac120002").matches()){
            throw new IllegalArgumentException("Invalid TaskId");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(value, taskId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
