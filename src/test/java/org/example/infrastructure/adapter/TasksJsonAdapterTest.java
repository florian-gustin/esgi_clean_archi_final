package org.example.infrastructure.adapter;

import com.google.gson.Gson;
import org.example.core.entity.Task;
import org.example.core.entity.Tasks;
import org.example.core.state.TaskState;
import org.example.core.validation.SimpleDate;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.infrastructure.data.TasksPersistenceObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TasksJsonAdapterTest {

    private final TasksJsonAdapter tasksJsonAdapter = new TasksJsonAdapter();
    private final Gson gson = new Gson();

    @Test
    void shouldConvertJsonToTasksObject() {
        // Given
        String json = "{\"data\":[" +
                "{\"id\":\"ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb\"," +
                "\"parentId\":null," +
                "\"createdDate\":\"2023-02-20\"," +
                "\"dueDate\":\"2023-01-20\"," +
                "\"content\":\"coucou\"," +
                "\"status\":\"TODO\"," +
                "\"tag\":null," +
                "\"subTask\":[]}]}";

        List<TaskPersistenceObject> taskPersistenceList = new ArrayList<>();
        TaskPersistenceObject taskPersistence = new TaskPersistenceObject();
        taskPersistence.setId("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb");
        taskPersistence.setParentId(null);
        taskPersistence.setCreatedDate("2023-02-20");
        taskPersistence.setDueDate("2023-01-20");
        taskPersistence.setContent("coucou");
        taskPersistence.setStatus("TODO");
        taskPersistence.setTag(null);
        taskPersistence.setSubTask(List.of());
        taskPersistenceList.add(taskPersistence);
        TasksPersistenceObject expectedTasksPersistence = new TasksPersistenceObject(taskPersistenceList);

        TasksPersistenceObject actualTasks = tasksJsonAdapter.convertToObject(json);
        // Then
        Assertions.assertEquals(expectedTasksPersistence, actualTasks);
    }

    @Test
    void shouldConvertTasksObjectToJson() {
        // Given
        String json = "{\"data\":[" +
                "{\"id\":\"ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb\"," +
                "\"parentId\":null," +
                "\"createdDate\":\"2023-02-20\"," +
                "\"dueDate\":\"2023-01-20\"," +
                "\"content\":\"coucou\"," +
                "\"status\":\"TODO\"," +
                "\"tag\":null," +
                "\"subTask\":[]}]}";

        List<TaskPersistenceObject> taskPersistenceList = new ArrayList<>();
        TaskPersistenceObject taskPersistence = new TaskPersistenceObject();
        taskPersistence.setId("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb");
        taskPersistence.setParentId(null);
        taskPersistence.setCreatedDate("2023-02-20");
        taskPersistence.setDueDate("2023-01-20");
        taskPersistence.setContent("coucou");
        taskPersistence.setStatus("TODO");
        taskPersistence.setTag(null);
        taskPersistence.setSubTask(List.of());
        taskPersistenceList.add(taskPersistence);
        TasksPersistenceObject tasksPersistence = new TasksPersistenceObject(taskPersistenceList);

        String actualJson = tasksJsonAdapter.convertToString(tasksPersistence);

        // Then
        Assertions.assertEquals(json, actualJson);
    }

}
