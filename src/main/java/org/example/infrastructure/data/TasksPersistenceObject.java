package org.example.infrastructure.data;

import java.util.List;

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
}
