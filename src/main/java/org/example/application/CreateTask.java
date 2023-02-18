package org.example.application;

import org.example.domain.Task;
import org.example.domain.TaskAdapter;
import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.UseCase;

import java.util.List;

public class CreateTask implements UseCase<TaskParams, Void> {

    private final TaskRepository taskRepository;

    public CreateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Void apply(TaskParams input) {
        // get tasks by user
        final List<Task> tasks = taskRepository.getTasks(input.user);
        // create new task
        tasks.add(Task.create(input.content, input.dueDate));
        // save tasks
        taskRepository.saveTask(tasks);

        return (Void)null;
    }
}
