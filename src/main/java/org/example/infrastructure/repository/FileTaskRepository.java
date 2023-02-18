package org.example.infrastructure.repository;

import org.example.domain.Task;
import org.example.domain.TaskId;
import org.example.domain.Tasks;
import org.example.infrastructure.TasksJsonAdapter;
import org.example.domain.interfaces.TaskRepository;
import org.example.kernel.Reader;
import org.example.kernel.Writer;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileTaskRepository implements TaskRepository {
    private final TasksJsonAdapter taskJsonAdapter;
    private final Writer fileWriter;
    private final Reader<String> fileReader;


    public FileTaskRepository(TasksJsonAdapter taskJsonAdapter, Writer fileWriter, Reader<String> fileReader) {
        this.taskJsonAdapter = taskJsonAdapter;
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
    }

    @Override
    public Tasks getAll() {
        String raw = fileReader.read();
        Tasks tasks = taskJsonAdapter.convertToObject(raw);
        return tasks;
    }

    @Override
    public Tasks update(Task task) {
        Tasks tasks = getAll();
        Tasks tasksUpdated = new Tasks(tasks.getData().stream().map(t -> t.equals(task) ? task : t).toList());
        // del file

        // writer
        String json = taskJsonAdapter.convertToString(tasksUpdated);
        try{
            fileWriter.write(json);
            return tasksUpdated;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean remove(String id) {
        Tasks tasks = getAll();
        Tasks tasksUpdated = new Tasks(tasks.getData().stream().reduce(t -> t. ? task : t).toList());
    }

    private Task findTaskById(List<Task> tasks, TaskId id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            } else {
                Task subTask = findTaskById(task.getSubTask(), id);
                if (Objects.nonNull(subTask)) {
                    return subTask;
                }
            }
        }
        return null;
    }

    private Task replaceTask(List<Task> tasks, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(updatedTask.getId())) {
                return task;
            } else {
                Task subTask = findTaskById(task.getSubTask(), id);
                if (Objects.nonNull(subTask)) {
                    return subTask;
                }
            }
        }
        return null;
    }
}

