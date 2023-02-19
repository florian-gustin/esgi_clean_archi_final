package org.example.domain.interfaces;

import org.example.domain.Task;
import org.example.domain.TaskId;
import org.example.domain.Tasks;

public interface TaskRepository {

    Tasks getAll();
    Task getTaskById(String taskId);
    Task updateTask(Task task);
    boolean removeTaskById(String taskId);
    Task addSubTask(Task subTask);
    Task addTask(Task task);

}
