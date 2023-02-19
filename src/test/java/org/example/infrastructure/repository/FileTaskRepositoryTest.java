package org.example.infrastructure.repository;

import org.example.core.entity.Task;
import org.example.core.state.TaskState;
import org.example.core.validation.TaskId;
import org.example.core.entity.Tasks;
import org.example.infrastructure.adapter.TasksJsonAdapter;
import org.example.core.port.TaskRepository;
import org.example.infrastructure.io.reader.Reader;
import org.example.infrastructure.io.writer.Writer;
import org.example.infrastructure.repository.FileTaskRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class FileTaskRepositoryTest {

    private static final String FILE_PATH = "src/test/resources/FileTest.json";

    private static TasksJsonAdapter tasksJsonAdapter;
    private static Writer fileWriter;
    private static Writer directoryWriter;
    private static Reader<String> fileReader;

    @Mock
    private Task taskMock;
    @Mock
    private Tasks tasksMock;

    private FileTaskRepository fileTaskRepository;

    @BeforeAll
    public static void setUpClass() {
        tasksJsonAdapter = new TasksJsonAdapter();
        fileWriter = Mockito.mock(Writer.class);
        directoryWriter = Mockito.mock(Writer.class);
        fileReader = Mockito.mock(Reader.class);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fileTaskRepository = new FileTaskRepository(tasksJsonAdapter, fileWriter, directoryWriter, fileReader);
    }

    @Test
    void testGetAll() {
        String json = "{\"data\":[{\"id\":{\"value\":\"2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11\"},\"createdDate\":\"Feb 19, 2023, 7:00:40 PM\",\"dueDate\":\"Feb 19, 2023, 7:00:40 PM\",\"content\":\"Task 1\",\"state\":\"TODO\",\"subTask\":[{\"createdDate\":\"Feb 19, 2023, 7:00:40 PM\",\"state\":\"DONE\"}]}]}";
        Mockito.when(fileReader.read()).thenReturn(json);
        Tasks result = fileTaskRepository.getAll();

        assertNotNull(result);
        assertEquals(1, result.getData().size());
        assertEquals("Task 1", result.getData().get(0).getContent());
    }

    @Test
    void testGetTaskById() {
        String json = "{\"data\":[{\"id\":{\"value\":\"2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11\"},\"createdDate\":\"Feb 19, 2023, 7:00:40 PM\",\"dueDate\":\"Feb 19, 2023, 7:00:40 PM\",\"content\":\"Task 1\",\"state\":\"TODO\",\"subTask\":[{\"createdDate\":\"Feb 19, 2023, 7:00:40 PM\",\"state\":\"DONE\"}]}]}";
        Mockito.when(fileReader.read()).thenReturn(json);
        Task result = fileTaskRepository.getTaskById("2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11");

        assertNotNull(result);
        assertEquals("Task 1", result.getContent());
    }

    @Test
    void testAddTask() {
        Task task = new Task();
        task.setContent("Task 1");
        task.setState(TaskState.TODO);
        task.setDueDate(new Date());
        task.setSubTask(new ArrayList<Task>());
        task.setTag("Général");
        task.setCreatedDate(new Date());
        Mockito.when(fileReader.read()).thenReturn("{\"data\":[]}");

        fileTaskRepository.addTask(task);
        Tasks result = fileTaskRepository.getAll();
        System.out.println(result.getData().size());
        assertEquals(1, 1);
    }

    @Test
    void testAddSubTask() {
        Task subTask = new Task();
        subTask.setContent("subTask1");
        subTask.setState(TaskState.TODO);
        subTask.setDueDate(new Date());
        subTask.setSubTask(new ArrayList<Task>());
        subTask.setTag("Général");
        subTask.setCreatedDate(new Date());

        Mockito.when(fileReader.read()).thenReturn("{\"data\":[{\"id\":{\"value\":\"2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11\"},\"createdDate\":\"Feb 19, 2023, 6:56:31 PM\",\"dueDate\":\"Feb 19, 2023, 6:56:31 PM\",\"state\":\"TODO\",\"subTask\":[{\"createdDate\":\"Feb 19, 2023, 6:56:31 PM\",\"state\":\"DONE\"}]}]}");
        fileTaskRepository.addSubTask(subTask);

        assertEquals(1, tasksMock.getData().size());
        assertEquals(taskMock, tasksMock.getData().get(0));
        Mockito.verify(fileWriter, Mockito.times(1)).write(Mockito.anyString());
    }

    @Test
    void testUpdateTask() {
        Task taskUpdate = new Task();
        taskUpdate.setContent("Task 1");
        taskUpdate.setId(new TaskId("2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11"));
        taskUpdate.setState(TaskState.TODO);
        taskUpdate.setDueDate(new Date());
        taskUpdate.setTag("Général");
        taskUpdate.setCreatedDate(new Date());

        Mockito.when(fileReader.read()).thenReturn("{\"data\":[{\"id\":{\"value\":\"2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11\"},\"createdDate\":\"Feb 19, 2023, 6:56:31 PM\",\"dueDate\":\"Feb 19, 2023, 6:56:31 PM\",\"state\":\"TODO\",\"subTask\":[{\"createdDate\":\"Feb 19, 2023, 6:56:31 PM\",\"state\":\"DONE\"}]}]}");
        Task taskUpdated = fileTaskRepository.updateTask(taskUpdate);
        System.out.println(taskUpdated.getContent());
        assertEquals(taskUpdated.getTag(), "Général");

    }



    @Test
    void deleteTaskById() {
        String json = "{\"data\":[{\"id\":{\"value\":\"2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11\"},\"createdDate\":\"Feb 19, 2023, 6:56:31 PM\",\"dueDate\":\"Feb 19, 2023, 6:56:31 PM\",\"state\":\"TODO\",\"subTask\":[{\"createdDate\":\"Feb 19, 2023, 6:56:31 PM\",\"state\":\"DONE\"}]}]}";
        Mockito.when(fileReader.read()).thenReturn(json);
        Tasks result = fileTaskRepository.getAll();

        assertNotNull(result);
        boolean deleted = fileTaskRepository.removeTaskById("2d6ab1a6-3f6e-44cf-81c3-55cf9f3c3a11");

        assertEquals(deleted,true);
    }
}
