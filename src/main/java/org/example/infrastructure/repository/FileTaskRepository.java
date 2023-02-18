package org.example.infrastructure.repository;

import org.example.domain.Task;
import org.example.domain.TaskAdapter;
import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.Reader;
import org.example.kernel.Writer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileTaskRepository implements TaskRepository {
    private final TaskAdapter taskAdapter;
    private final Writer fileWriter;
    private final Reader<List<Map<?, ?>>> fileReader;


    public FileTaskRepository(TaskAdapter taskAdapter, Writer fileWriter, Reader<List<Map<?, ?>>> fileReader) {
        this.taskAdapter = taskAdapter;
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
    }

    public List<Task> getTasks(String user) {
        final List<Map<?, ?>> json = fileReader.read();
        final List<Task> tasks = json.stream().map(task -> taskAdapter.read(task)).collect(Collectors.toList());
        return tasks;

    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    public List<Task> saveTask(List<Task> tasks) {
        final List<Map<?, ?>> json = tasks.stream().map(task -> taskAdapter.write(task)).collect(Collectors.toList());
        fileWriter.write(json);
        return tasks;
    }
}
