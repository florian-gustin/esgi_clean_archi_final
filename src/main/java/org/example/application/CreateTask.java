package org.example.application;

import org.example.domain.Task;
import org.example.domain.Tasks;
import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.UseCase;

public class CreateTask implements UseCase<TaskParams, Void> {

    private final TaskRepository taskRepository;

    public CreateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Void apply(TaskParams input) {
        // get tasks by user
        final Tasks tasks = taskRepository.getTasks();
        // create new task
        tasks.getData().add(Task.create(input.content, input.dueDate));
        // save tasks
        Tasks tasksSaved = taskRepository.saveTask(tasks);

        return (Void)null;
    }
}
