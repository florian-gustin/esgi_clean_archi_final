package org.example.core.usecases;

import org.example.core.entity.Task;
import org.example.core.entity.Tasks;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;

import java.util.Objects;

public class CreateTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;

    public CreateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Void apply(TaskDTO input) {
        Task createdTask = taskRepository.addTask(Task.create(input.content, input.dueDate, input.tag));
        // afficher
        return (Void) null;
    }
}
