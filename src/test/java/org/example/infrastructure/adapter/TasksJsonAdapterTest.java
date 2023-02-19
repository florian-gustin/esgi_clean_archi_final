package org.example.infrastructure.adapter;

import com.google.gson.Gson;
import org.example.core.entity.Task;
import org.example.core.entity.Tasks;
import org.example.core.state.TaskState;
import org.example.core.validation.TaskId;
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
        String json = "{\"data\":[{\"state\":\"TODO\",\"subTask\":[{\"state\":\"DONE\"}]}]}";
        System.out.println(json);
        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setState(TaskState.TODO);


        List<Task> subTasks = new ArrayList<>();
        Task subTask = new Task();
        subTask.setState(TaskState.DONE);
        subTasks.add(subTask);
        task.setSubTask(subTasks);
        tasks.add(task);
        Tasks expectedTasks = new Tasks(tasks);
        Tasks actualTasks = tasksJsonAdapter.convertToObject(json);
        // Then
        Assertions.assertEquals(actualTasks.getData().get(0).getState(),expectedTasks.getData().get(0).getState());
    }

    @Test
    void shouldConvertTasksObjectToJson() {
        // Given
        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setId(new TaskId("2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11"));
        task.setContent("Task 1");
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
        System.out.println(actualJson);

        // Then
        Assertions.assertEquals(expectedJson, actualJson);
    }

}
