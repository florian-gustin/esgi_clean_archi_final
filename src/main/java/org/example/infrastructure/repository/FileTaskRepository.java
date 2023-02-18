package org.example.infrastructure.repository;

import org.example.domain.Tasks;
import org.example.domain.TasksJsonAdapter;
import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.Reader;
import org.example.kernel.Writer;

import java.util.List;
import java.util.Map;

public class FileTaskRepository implements TaskRepository {
    private final TasksJsonAdapter taskJsonAdapter;
    private final Writer fileWriter;
    private final Reader<List<Map<?, ?>>> fileReader;


    public FileTaskRepository(TasksJsonAdapter taskJsonAdapter, Writer fileWriter, Reader<List<Map<?, ?>>> fileReader) {
        this.taskJsonAdapter = taskJsonAdapter;
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
    }

    @Override
    public Tasks getTasks() {
        return null;
    }

    public Tasks saveTask(Tasks tasks) {
        final String json = taskJsonAdapter.convertToString(tasks);
        fileWriter.write(json);
        return tasks;
    }
}
