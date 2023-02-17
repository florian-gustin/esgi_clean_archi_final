package org.example.domain.interfaces;

import org.example.domain.Task;

import java.util.List;

public interface ITaskService {

    void createTask(Task task);

    List<Task> getTasks();

    void updateTask(int id, Task task);

    void deleteTask(int id);
}
