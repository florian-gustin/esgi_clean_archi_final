package org.example.infrastructure.data;

import java.util.List;
import java.util.Objects;

public class TasksPersistenceObject {
    private List<TaskPersistenceObject> data;

    public List<TaskPersistenceObject> getData() {
        return data;
    }

    public void setData(List<TaskPersistenceObject> data) {
        this.data = data;
    }

    public TasksPersistenceObject(List<TaskPersistenceObject> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TasksPersistenceObject that)) return false;
        return Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
