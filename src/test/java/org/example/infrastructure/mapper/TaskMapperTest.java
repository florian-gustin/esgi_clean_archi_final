package org.example.infrastructure.mapper;

import org.example.core.entity.Task;
import org.example.core.state.TaskState;
import org.example.core.validation.SimpleDate;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    Task createTask() {

        Task task = new Task();
        task.setParentId(null);
        task.setCreatedDate(new SimpleDate("2023-02-20"));
        task.setDueDate(new SimpleDate("2023-02-21"));
        task.setContent("coucou");
        task.setState(TaskState.DONE);
        task.setTag(null);

        return task;
    }

    TaskPersistenceObject createTaskPersistence() {
        TaskPersistenceObject persistenceObject = new TaskPersistenceObject();
        persistenceObject.setParentId(null);
        persistenceObject.setCreatedDate("2023-02-20");
        persistenceObject.setDueDate("2023-02-21");
        persistenceObject.setContent("coucou");
        persistenceObject.setStatus("DONE");
        persistenceObject.setTag(null);

        return persistenceObject;
    }

    @Test
    void taskToPersistenceObject() {
        TaskMapper taskMapper = new TaskMapper();

        Task task = createTask();

        TaskPersistenceObject actualObject = taskMapper.toPersistenceObject(task);

        TaskPersistenceObject exceptedObject = createTaskPersistence();

        assertEquals(exceptedObject, actualObject);

    }

    @Test
    void persistenceObjectToTask() {

        TaskMapper taskMapper = new TaskMapper();
        Task task = createTask();

        TaskPersistenceObject persistenceObject = createTaskPersistence();

        Task excepted = taskMapper.toEntity(persistenceObject);

        assertEquals(task, excepted);

    }
}
