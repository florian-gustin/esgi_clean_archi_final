package org.example.core.usecases;

import org.example.core.entity.Task;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;

public class UpdateTask implements UseCase<TaskDTO, Task> {

    private final TaskRepository taskRepository;

    public UpdateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task apply(TaskDTO input) {
        Task task = taskRepository.getTaskById(input.taskId);
        task.update(input.content, input.dueDate, input.status, input.tag);
        Task updateTask = taskRepository.updateTask(task);
        return updateTask;
    }
}
