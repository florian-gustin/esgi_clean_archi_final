package org.example.infrastructure.repository;

import org.example.core.entity.Task;
import org.example.core.validation.TaskId;
import org.example.core.entity.Tasks;
import org.example.infrastructure.adapter.TasksJsonAdapter;
import org.example.core.port.TaskRepository;
import org.example.infrastructure.config.Constants;
import org.example.core.exception.TaskException;
import org.example.infrastructure.io.reader.Reader;
import org.example.infrastructure.io.writer.Writer;

import java.util.*;


public class FileTaskRepository implements TaskRepository {
    private final TasksJsonAdapter taskJsonAdapter;
    private final Writer fileWriter;
    private final Writer directoryWriter;
    private final Reader<String> fileReader;




    public FileTaskRepository(TasksJsonAdapter taskJsonAdapter, Writer fileWriter, Writer directoryWriter, Reader<String> fileReader) {
        this.taskJsonAdapter = taskJsonAdapter;
        this.fileWriter = fileWriter;
        this.directoryWriter = directoryWriter;
        this.fileReader = fileReader;
        initDirectory();
    }

    @Override
    public Tasks getAll() {
        String raw = fileReader.read();
        Tasks tasks = taskJsonAdapter.convertToObject(raw);
        return tasks;
    }

    @Override
    public Task getTaskById(String taskId) {
        try{
            Tasks tasks = getAll();
            Task task = findTaskById(tasks.getData(), taskId);
            if(Objects.isNull(task)){
                throw new TaskException("la tache n'a pas été trouvé");
            }
            return task;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task updateTask(Task task) {
        try{
            Tasks tasks = getAll();
            List<Task> taskList = updateTaskAndGetList(tasks.getData(), task);
            String json = taskJsonAdapter.convertToString(new Tasks(taskList));
            fileWriter.write(json);
            tasks = getAll();
            Task updatedTask = findTaskById(tasks.getData(), task.getId().getValue());
            if(Objects.isNull(updatedTask)){
                throw new TaskException("la tache n'a pas été mis a jour");
            }
            return updatedTask;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean removeTaskById(String taskId) {
        try{
            Tasks tasks = getAll();
            System.out.println(tasks.getData().get(0).getId().getValue());
            List<Task> taskList = deleteTaskById(tasks.getData(), taskId);
            String json = taskJsonAdapter.convertToString(new Tasks(taskList));
            fileWriter.write(json);
            tasks = getAll();
            boolean isDeleted = Objects.isNull(findTaskById(tasks.getData(), taskId));
            if(!isDeleted){
                throw new TaskException("la tache n'a pas été supprimé");
            }
            return true;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task addSubTask(Task subTask) {
        try{
            Tasks tasks = getAll();
            List<Task> taskList = addTaskAndGetList(tasks.getData(), subTask);
            String json = taskJsonAdapter.convertToString(new Tasks(taskList));
            fileWriter.write(json);
            tasks = getAll();
            Task updatedTask = findTaskById(tasks.getData(), subTask.getId().getValue());
            if(Objects.isNull(updatedTask)){
                throw new TaskException("la tache n'a pas été ajouté");
            }
            return updatedTask;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task addTask(Task task) {
        try{
            Tasks tasks = getAll();
            if(Objects.isNull(tasks)){
                tasks = new Tasks(new ArrayList<>());
            }
            task.setId(new TaskId(UUID.randomUUID().toString()));
            tasks.getData().add(task);
            String json = taskJsonAdapter.convertToString(tasks);
            fileWriter.write(json);
            Task updatedTask = findTaskById(tasks.getData(), task.getId().getValue());
            if(Objects.isNull(updatedTask)){
                throw new TaskException("la tache n'a pas été ajouté");
            }
            return updatedTask;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    private Task findTaskById(List<Task> tasks, String taskId) {
        for (Task task : tasks) {
            if (task.getId().getValue().equals(taskId)) {
                return task;
            } else {
                Task subTask = findTaskById(task.getSubTask(), taskId);
                if (Objects.nonNull(subTask)) {
                    return subTask;
                }
            }
        }
        return null;
    }

    private List<Task> deleteTaskById(List<Task> tasks, String taskId) {
        for (Task task : tasks) {
            if (task.getId().getValue().equals(taskId)) {
                tasks.remove(task);
                return tasks;
            } else {
                List<Task> subTasks = task.getSubTask();
                if (subTasks != null && !subTasks.isEmpty()) {
                    List<Task> updatedSubTasks = deleteTaskById(subTasks, taskId);
                    if (Objects.nonNull(updatedSubTasks) && !updatedSubTasks.isEmpty()) {
                        subTasks.remove(updatedSubTasks);
                        return tasks;
                    }
                }
            }
        }
        return null;
    }

    private List<Task> updateTaskAndGetList(List<Task> tasks, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(updatedTask.getId())) {
                task.setContent(updatedTask.getContent());
                task.setDueDate(updatedTask.getDueDate());
                task.setState(updatedTask.getState());
                task.setTag(updatedTask.getTag());
                task.setSubTask(updatedTask.getSubTask());
                return tasks;
            } else {
                List<Task> subTasks = task.getSubTask();
                if (subTasks != null && !subTasks.isEmpty()) {
                    List<Task> updatedSubTasks = updateTaskAndGetList(subTasks, updatedTask);
                    if (Objects.nonNull(updatedSubTasks) && !updatedSubTasks.isEmpty()) {
                        task.setSubTask(updatedSubTasks);
                        return tasks;
                    }
                }
            }
        }
        return null;
    }

    private List<Task> addTaskAndGetList(List<Task> tasks, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(updatedTask.getParentId())) {
                updatedTask.setId(new TaskId(UUID.randomUUID().toString()));
                task.getSubTask().add(updatedTask);
                return tasks;
            } else {
                List<Task> subTasks = task.getSubTask();
                if (subTasks != null && !subTasks.isEmpty()) {
                    List<Task> updatedSubTasks = updateTaskAndGetList(subTasks, updatedTask);
                    if (Objects.nonNull(updatedSubTasks) && !updatedSubTasks.isEmpty()) {
                        updatedTask.setId(new TaskId(UUID.randomUUID().toString()));
                        updatedSubTasks.add(updatedTask);
                        return tasks;
                    }
                }
            }
        }
        return null;
    }

    private void initDirectory() {
        directoryWriter.write(Constants.DIRECTORY_PATH);
    }
}

