package org.example.infrastructure.adapter;

import com.google.gson.Gson;
import org.example.core.entity.Task;
import org.example.core.entity.Tasks;
import org.example.core.state.TaskState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TasksJsonAdapterTest {

    private final TasksJsonAdapter tasksJsonAdapter = new TasksJsonAdapter();
    private final Gson gson = new Gson();

    @Test
    void shouldConvertJsonToTasksObject() {
        // Given
        String json = "{\"data\":[{\"created\":\"2022-02-15T22:14:30.486798+01:00\",\"dueDate\":\"2022-02-16T14:14:30.535929+01:00\",\"closeDate\":null,\"description\":\"init a project to create an app for my tasks\",\"state\":0,\"tag\":null,\"subTasks\":[{\"created\":\"2022-02-15T22:14:30.535975+01:00\",\"dueDate\":null,\"closeDate\":null,\"description\":\"init repo for the project\",\"state\":3,\"tag\":null,\"subTasks\":null}]}]}";

        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setContent("init a project to create an app for my tasks");
        task.setCreatedDate(new Date("2022-02-15T22:14:30.486798+01:00"));
        task.setDueDate(new Date("2022-02-16T14:14:30.535929+01:00"));
        task.setState(TaskState.TODO);

        List<Task> subTasks = new ArrayList<>();
        Task subTask = new Task();
        subTask.setContent("init repo for the project");
        subTask.setCreatedDate(new Date("2022-02-15T22:14:30.535975+01:00"));
        subTask.setState(TaskState.DONE);
        subTasks.add(subTask);
        task.setSubTask(subTasks);

        tasks.add(task);
        Tasks expectedTasks = new Tasks(tasks);

        // When
        Tasks actualTasks = tasksJsonAdapter.convertToObject(json);

        // Then
        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void shouldConvertTasksObjectToJson() {
        // Given
        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setCreatedDate(new Date());
        task.setDueDate(new Date());
        task.setState(TaskState.TODO);

        List<Task> subTasks = new ArrayList<>();
        Task subTask = new Task();
        subTask.setCreatedDate(new Date());
        subTask.setState(TaskState.DONE);
        subTasks.add(subTask);
        task.setSubTask(subTasks);

        tasks.add(task);
        Tasks tasksObj = new Tasks(tasks);

        String expectedJson = gson.toJson(tasksObj);

        // When
        String actualJson = tasksJsonAdapter.convertToString(tasksObj);

        // Then
        Assertions.assertEquals(expectedJson, actualJson);
    }
}