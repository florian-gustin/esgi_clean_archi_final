package org.example.core.usecases;

import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;

public class RemoveTask implements UseCase<TaskDTO, Boolean> {
    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Boolean apply(TaskDTO input) {
        boolean isDeleted = taskRepository.removeTaskById(input.taskId);
        return isDeleted;
    }
}
