package org.example.domain;

import org.example.domain.interfaces.TaskRepository;

import java.util.List;

public class TaskService implements org.example.domain.interfaces.TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    public List<Task> saveTask(List<Task> tasks) {
        return taskRepository.saveTask(tasks);
    }
}
