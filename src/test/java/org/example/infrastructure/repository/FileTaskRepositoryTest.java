///*
//package org.example.infrastructure.repository;
//
//
//import org.example.MockitoExtension;
//import org.example.core.entity.Task;
//import org.example.core.entity.Tasks;
//import org.example.core.port.TaskRepository;
//import org.example.infrastructure.adapter.TasksJsonAdapter;
//import org.example.infrastructure.io.reader.FileReader;
//import org.example.infrastructure.io.writer.FileWriter;
//import org.example.infrastructure.repository.FileTaskRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.io.IOException;
//
//@ExtendWith(MockitoExtension.class)
//public class FileTaskRepositoryTest {
//
//    private final TaskRepository taskRepository;
//
//
//
//    public FileTaskRepositoryTest() throws IOException {
//        this.taskRepository = new FileTaskRepository(new TasksJsonAdapter(), new FileWriter(), directoryWriter, new FileReader());
//    }
//
//
//    @Test
//    void testGetAllSuccess() {
//        Task newTask = Task.create("test", "");
//        taskRepository.addTask(newTask);
//        Tasks tasks = taskRepository.getAll();
//        Assertions.assertNotNull(tasks);
//    }
//
//}
//*/
