
package org.example.infrastructure.repository;


import org.example.MockitoExtension;
import org.example.core.port.TaskRepository;
import org.example.infrastructure.adapter.TasksJsonAdapter;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.infrastructure.data.TasksPersistenceObject;
import org.example.infrastructure.io.reader.FileReader;
import org.example.infrastructure.io.writer.DirectoryWriter;
import org.example.infrastructure.io.writer.FileWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FileTaskRepositoryTest {

    private final TaskRepository taskRepository = new FileTaskRepository(
            new TasksJsonAdapter(),
            new FileWriter("src/test/resources/TaskRepositoryTest.json"),
            new DirectoryWriter(),
            new FileReader("src/test/resources/TaskRepositoryTest.json")
    );

    private static final TaskPersistenceObject taskPersistenceObjectOne = new TaskPersistenceObject();

    private static final TaskPersistenceObject taskPersistenceObjectTwo = new TaskPersistenceObject();
    private static final TaskPersistenceObject taskPersistenceObjectThree = new TaskPersistenceObject();
    private static final TaskPersistenceObject taskToAdd = new TaskPersistenceObject();


    private static void initTaskPersistenceObjects() {
        taskPersistenceObjectOne.setId("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb");
        taskPersistenceObjectOne.setParentId(null);
        taskPersistenceObjectOne.setCreatedDate("2023-02-20");
        taskPersistenceObjectOne.setDueDate("2023-01-20");
        taskPersistenceObjectOne.setContent("coucou");
        taskPersistenceObjectOne.setStatus("TODO");
        taskPersistenceObjectOne.setTag(null);
        taskPersistenceObjectOne.setSubTask(List.of());

        taskPersistenceObjectTwo.setId("d0faa257-f462-4c71-8dd2-f527883c293a");
        taskPersistenceObjectTwo.setParentId(null);
        taskPersistenceObjectTwo.setCreatedDate("2023-02-20");
        taskPersistenceObjectTwo.setDueDate(null);
        taskPersistenceObjectTwo.setContent("SALUT");
        taskPersistenceObjectTwo.setStatus("TODO");
        taskPersistenceObjectTwo.setTag(null);
        taskPersistenceObjectTwo.setSubTask(List.of());

        taskPersistenceObjectThree.setId("81e3ebdb-da59-4f70-94ce-3cc751966354");
        taskPersistenceObjectThree.setParentId(null);
        taskPersistenceObjectThree.setCreatedDate("2023-02-20");
        taskPersistenceObjectThree.setDueDate(null);
        taskPersistenceObjectThree.setContent("plaspasasas");
        taskPersistenceObjectThree.setStatus("TODO");
        taskPersistenceObjectThree.setTag(null);
        taskPersistenceObjectThree.setSubTask(List.of());

        taskToAdd.setParentId(null);
        taskToAdd.setCreatedDate("2023-02-20");
        taskToAdd.setDueDate(null);
        taskToAdd.setContent("task to add");
        taskToAdd.setStatus("TODO");
        taskToAdd.setTag(null);
        taskToAdd.setSubTask(List.of());
    }

    public FileTaskRepositoryTest() throws IOException {
    }

    @BeforeAll
    public static void init() {
        initTaskPersistenceObjects();
    }

    @BeforeEach
    public void initTest() throws IOException {
        initTaskPersistenceObjects();
        new FileWriter("src/test/resources/TaskRepositoryTest.json").write(new FileReader("src/test/resources/TaskRepositoryTestBackup.json").read());
    }

    @Test
    public void getAllMethod() {
        TasksPersistenceObject tasks = taskRepository.getAll();
        TasksPersistenceObject tasksExpected = new TasksPersistenceObject(List.of(
                taskPersistenceObjectOne,
                taskPersistenceObjectTwo,
                taskPersistenceObjectThree
        ));
        Assertions.assertEquals(tasksExpected.getData().size(), tasks.getData().size());
        Assertions.assertEquals(tasksExpected, tasks);
    }

    @Test
    public void addTaskMethod() {
        taskRepository.addTask(taskToAdd);
        TasksPersistenceObject tasks = taskRepository.getAll();
        TasksPersistenceObject tasksExpected = new TasksPersistenceObject(List.of(
                taskPersistenceObjectOne,
                taskPersistenceObjectTwo,
                taskPersistenceObjectThree,
                taskToAdd
        ));
        Assertions.assertEquals(tasksExpected.getData().size(), tasks.getData().size());
        Assertions.assertEquals(tasksExpected, tasks);
    }

    @Test
    public void getByIdMethod() {
        TaskPersistenceObject task = taskRepository.getTaskById("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb");
        Assertions.assertEquals(taskPersistenceObjectOne, task);
    }

    @Test
    public void updateMethod() {
        TaskPersistenceObject task = taskRepository.getTaskById("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb");
        task.setContent("azzerty");
        taskRepository.updateTask(task);
        Assertions.assertEquals(task.getContent(), taskRepository.getTaskById("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb").getContent());
    }

    @Test
    public void deleteMethod() {
        taskRepository.removeTaskById("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb");
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> taskRepository.getTaskById("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb"));
        TasksPersistenceObject tasks = taskRepository.getAll();

        Assertions.assertEquals(2, tasks.getData().size());
        Assertions.assertEquals("la tache n'a pas été trouvé", exception.getMessage());
    }

    @Test
    public void addSubtaskMethod() {
        TaskPersistenceObject task = taskPersistenceObjectOne;
        task.setParentId(task.getId());
        task.setId(null);
        task.setContent("subtask");
        taskRepository.addSubTask(task);
        Assertions.assertEquals(taskRepository.getTaskById("ebd5f0d8-ea8a-44e5-be9d-5d73e059afeb").getSubTask().get(0), task);
    }

}

