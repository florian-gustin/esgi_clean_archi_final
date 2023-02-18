package org.example.application;

import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.UseCase;

public class RemoveTask implements UseCase<TaskParams, String> {
    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String apply(TaskParams input) {
        return null;
    }
}
