package org.example.application;

import org.example.domain.Task;
import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.UseCase;

public class UpdateTask implements UseCase<TaskParams, Task> {

    private final TaskRepository taskRepository;

    public UpdateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task apply(TaskParams input) {
        // TODO : get task by id
        final Task placeholder = Task.create("change me", null);

        return taskRepository.update(placeholder);
    }
}
