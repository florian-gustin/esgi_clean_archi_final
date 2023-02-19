package org.example.infrastructure;


import org.example.MockitoExtension;
import org.example.domain.Task;
import org.example.domain.Tasks;
import org.example.domain.interfaces.TaskRepository;
import org.example.infrastructure.io.FileReader;
import org.example.infrastructure.io.FileWriter;
import org.example.infrastructure.repository.FileTaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class FileTaskRepositoryTest {

    private final TaskRepository taskRepository;



    public FileTaskRepositoryTest() {
        this.taskRepository = new FileTaskRepository(new TasksJsonAdapter(), new FileWriter(), new FileReader());
    }


    @Test
    void testGetAllSuccess() {
        Task newTask = Task.create("test", "");
        taskRepository.addTask(newTask);
        Tasks tasks = taskRepository.getAll();
        Assertions.assertNotNull(tasks);
    }

}
