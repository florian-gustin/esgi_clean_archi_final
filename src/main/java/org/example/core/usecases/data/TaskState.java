package org.example.core.usecases.data;

import org.example.core.entity.Task;

public class TaskState {

    public Task task;
    public boolean isOverDue;

    public TaskState(Task task, boolean isOverDue) {
        this.task = task;
        this.isOverDue = isOverDue;
    }
}
