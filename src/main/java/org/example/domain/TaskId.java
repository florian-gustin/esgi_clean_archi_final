package org.example.domain;

import org.example.domain.interfaces.ValueObjectId;

import java.util.Objects;

public class TaskId implements ValueObjectId {

    private final String value;

    public TaskId(String value) {
        this.value = value;
    }

    public String getValue() {
        return null;
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
        return "TaskId{" +
                "value='" + value + '\'' +
                '}';
    }
}
