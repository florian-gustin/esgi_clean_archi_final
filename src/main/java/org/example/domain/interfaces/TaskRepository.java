package org.example.domain.interfaces;

import org.example.domain.Task;

import java.util.List;

public interface TaskRepository {

    void getTasks();
    void saveTask(List<Task> tasks);

}
