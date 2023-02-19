package org.example.core.port;

import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.infrastructure.data.TasksPersistenceObject;

public interface TaskRepository {

    /**
     * Get all tasks
     * @return
     */
    TasksPersistenceObject getAll();

    /**
     * Get a task by id
     * @param taskId
     * @return
     */
    TaskPersistenceObject getTaskById(String taskId);

    /**
     * Update a task
     * @param task
     * @return
     */
    TaskPersistenceObject updateTask(TaskPersistenceObject task);

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
    TaskPersistenceObject addSubTask(TaskPersistenceObject subTask);

    /**
     * Add a task
     * @param task
     * @return
     */
    TaskPersistenceObject addTask(TaskPersistenceObject task);

}
