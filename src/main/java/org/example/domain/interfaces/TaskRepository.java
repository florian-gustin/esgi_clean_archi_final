package org.example.domain.interfaces;

import org.example.domain.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getTasks(String user);
    List<Task> getTasks();
    List<Task> saveTask(List<Task> tasks);

}
