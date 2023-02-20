package org.example.core.entity;

import java.util.List;

public class Tasks {

    private List<Task> data;

    public Tasks(List<Task> tasks) {
        this.data = tasks;
    }

    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "data=" + data +
                '}';
    }
}
