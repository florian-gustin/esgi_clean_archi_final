package org.example.core.usecases;

import org.example.core.entity.Task;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;

public class CreateSubTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;

    public CreateSubTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Void apply(TaskDTO input) {
        Task parentTask = taskRepository.getTaskById(input.parentId);
        parentTask.createSubTask(input.content, input.dueDate, input.tag);
        taskRepository.updateTask(parentTask);
        // afficher
        return (Void) null;
    }
}
