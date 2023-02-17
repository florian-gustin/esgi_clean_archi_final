package org.example.domain;

import java.util.List;

public interface TaskRepository {

    void getTasks();
    void saveTask(List<Task> tasks);

}
