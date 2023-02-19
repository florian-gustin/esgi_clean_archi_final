package org.example.application;

import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.UseCase;

public class RemoveTask implements UseCase<TaskParams, Boolean> {
    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Boolean apply(TaskParams input) {
        // check if task exist

        return this.taskRepository.remove(input.taskId);

    }
}
