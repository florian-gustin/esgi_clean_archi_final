//package org.example.infrastructure.adapter;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.example.core.entity.Tasks;
//import org.junit.jupiter.api.Test;
//
//public class TaskJsonAdapterTest {
//
//    private final TasksJsonAdapter tasksJsonAdapter = new TasksJsonAdapter();
//
//    @Test
//    public void testConvertToObject() {
//        String json = "{\n" +
//                "  \"tasks\": [\n" +
//                "    {\n" +
//                "      \"Created\": \"2022-02-15T22:14:30.486798+01:00\",\n" +
//                "      \"dueDate\": \"2022-02-16T14:14:30.535929+01:00\",\n" +
//                "      \"closeDate\": null,\n" +
//                "      \"description\": \"init a project to create an app for my tasks\",\n" +
//                "      \"state\": 0,\n" +
//                "      \"tag\": null,\n" +
//                "      \"subTasks\": [\n" +
//                "        {\n" +
//                "          \"Created\": \"2022-02-15T22:14:30.535975+01:00\",\n" +
//                "          \"dueDate\": null,\n" +
//                "          \"closeDate\": null,\n" +
//                "          \"description\": \"init repo for the project\",\n" +
//                "          \"state\": 3,\n" +
//                "          \"tag\": null,\n" +
//                "          \"subTasks\": null\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        Tasks tasks = tasksJsonAdapter.convertToObject(json);
//        assertEquals(1, tasks.getData().stream.getId());
//        assertEquals("Task 1", tasks.getName());
//        assertEquals("Description for Task 1", tasks.getDescription());
//    }
//
//    @Test
//    public void testConvertToString() {
//        Tasks tasks = new Tasks(1, "Task 1", "Description for Task 1");
//        String json = tasksJsonAdapter.convertToString(tasks);
//        String expectedJson = "{\"id\":1,\"name\":\"Task 1\",\"description\":\"Description for Task 1\"}";
//        assertEquals(expectedJson, json);
//    }
//    *
//}
