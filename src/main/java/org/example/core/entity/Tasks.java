package org.example.core.entity;

import java.util.List;

public class Tasks {

    private List<Task> data;

    public Tasks(List<Task> tasks) {
        this.data = tasks;
    }

    public static Tasks create(List<Task> tasks) {
        return new Tasks(tasks);
    }

    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }


}
