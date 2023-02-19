package org.example.core.usecases;

import org.example.core.entity.Task;
import org.example.core.entity.Tasks;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;

public class ListTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;

    public ListTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Void apply(TaskDTO input) {
        final Tasks tasks = taskRepository.getAll();
        // afficher
        return (Void) null;
    }
}
