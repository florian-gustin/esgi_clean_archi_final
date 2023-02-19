package org.example.domain;

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
}
