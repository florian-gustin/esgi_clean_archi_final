package org.example.core.port;

import org.example.core.entity.Task;
import org.example.core.entity.Tasks;

public interface TaskRepository {

    /**
     * Get all tasks
     * @return
     */
    Tasks getAll();

    /**
     * Get a task by id
     * @param taskId
     * @return
     */
    Task getTaskById(String taskId);

    /**
     * Update a task
     * @param task
     * @return
     */
    Task updateTask(Task task);

    /**
     * Remove a task by id
     * @param taskId
     * @return
     */
    boolean removeTaskById(String taskId);

    /**
     * Add a sub task
     * @param subTask
     * @return
     */
    Task addSubTask(Task subTask);

    /**
     * Add a task
     * @param task
     * @return
     */
    Task addTask(Task task);

}
