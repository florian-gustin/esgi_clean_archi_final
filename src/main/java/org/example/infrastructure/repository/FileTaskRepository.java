package org.example.infrastructure.repository;

import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.infrastructure.data.TasksPersistenceObject;
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
    public TasksPersistenceObject getAll() {
        String raw = fileReader.read();
        TasksPersistenceObject tasks = taskJsonAdapter.convertToObject(raw);
        return tasks;
    }

    @Override
    public TaskPersistenceObject getTaskById(String taskId) {
        try{
            TasksPersistenceObject tasks = getAll();
            TaskPersistenceObject task = findTaskById(tasks.getData(), taskId);
            if(Objects.isNull(task)){
                throw new TaskException("la tache n'a pas été trouvé");
            }
            return task;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TaskPersistenceObject updateTask(TaskPersistenceObject task) {
        try{
            TasksPersistenceObject tasks = getAll();
            List<TaskPersistenceObject> taskList = updateTaskAndGetList(tasks.getData(), task);
            String json = taskJsonAdapter.convertToString(new TasksPersistenceObject(taskList));
            fileWriter.write(json);
            tasks = getAll();
            TaskPersistenceObject updatedTask = findTaskById(tasks.getData(), task.getId());
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
            TasksPersistenceObject tasks = getAll();
            List<TaskPersistenceObject> taskList = deleteTaskById(tasks.getData(), taskId);
            String json = taskJsonAdapter.convertToString(new TasksPersistenceObject(taskList));
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
    public TaskPersistenceObject addSubTask(TaskPersistenceObject subTask) {
        try{
            TasksPersistenceObject tasks = getAll();
            List<TaskPersistenceObject> taskList = addTaskAndGetList(tasks.getData(), subTask);
            String json = taskJsonAdapter.convertToString(new TasksPersistenceObject(taskList));
            fileWriter.write(json);
            tasks = getAll();
            TaskPersistenceObject updatedTask = findTaskById(tasks.getData(), subTask.getId());
            if(Objects.isNull(updatedTask)){
                throw new TaskException("la tache n'a pas été ajouté");
            }
            return updatedTask;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TaskPersistenceObject addTask(TaskPersistenceObject task) {
        try{
            TasksPersistenceObject tasks = getAll();
            if(Objects.isNull(tasks)){
                tasks = new TasksPersistenceObject(new ArrayList<>());
            }
            task.setId(UUID.randomUUID().toString());
            tasks.getData().add(task);
            String json = taskJsonAdapter.convertToString(tasks);
            fileWriter.write(json);
            TaskPersistenceObject updatedTask = findTaskById(tasks.getData(), task.getId());
            if(Objects.isNull(updatedTask)){
                throw new TaskException("la tache n'a pas été ajouté");
            }
            return updatedTask;
        }catch (TaskException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    private TaskPersistenceObject findTaskById(List<TaskPersistenceObject> tasks, String taskId) {
        for (TaskPersistenceObject task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            } else {
                TaskPersistenceObject subTask = findTaskById(task.getSubTask(), taskId);
                if (Objects.nonNull(subTask)) {
                    return subTask;
                }
            }
        }
        return null;
    }

    private List<TaskPersistenceObject> deleteTaskById(List<TaskPersistenceObject> tasks, String taskId) {
        for (TaskPersistenceObject task : tasks) {
            if (task.getId().equals(taskId)) {
                tasks.remove(task);
                return tasks;
            } else {
                List<TaskPersistenceObject> subTasks = task.getSubTask();
                if (subTasks != null && !subTasks.isEmpty()) {
                    List<TaskPersistenceObject> updatedSubTasks = deleteTaskById(subTasks, taskId);
                    if (Objects.nonNull(updatedSubTasks) && !updatedSubTasks.isEmpty()) {
                        subTasks.remove(updatedSubTasks);
                        return tasks;
                    }
                }
            }
        }
        return null;
    }

    private List<TaskPersistenceObject> updateTaskAndGetList(List<TaskPersistenceObject> tasks, TaskPersistenceObject updatedTask) {
        for (TaskPersistenceObject task : tasks) {
            if (task.getId().equals(updatedTask.getId())) {
                task.copyWith(updatedTask);
                return tasks;
            } else {
                List<TaskPersistenceObject> subTasks = task.getSubTask();
                if (subTasks != null && !subTasks.isEmpty()) {
                    List<TaskPersistenceObject> updatedSubTasks = updateTaskAndGetList(subTasks, updatedTask);
                    if (Objects.nonNull(updatedSubTasks) && !updatedSubTasks.isEmpty()) {
                        task.setSubTask(updatedSubTasks);
                        return tasks;
                    }
                }
            }
        }
        return null;
    }

    private List<TaskPersistenceObject> addTaskAndGetList(List<TaskPersistenceObject> tasks, TaskPersistenceObject updatedTask) {
        for (TaskPersistenceObject task : tasks) {
            if (task.getId().equals(updatedTask.getParentId())) {
                updatedTask.setId(UUID.randomUUID().toString());
                task.getSubTask().add(updatedTask);
                return tasks;
            } else {
                List<TaskPersistenceObject> subTasks = task.getSubTask();
                if (subTasks != null && !subTasks.isEmpty()) {
                    List<TaskPersistenceObject> updatedSubTasks = updateTaskAndGetList(subTasks, updatedTask);
                    if (Objects.nonNull(updatedSubTasks) && !updatedSubTasks.isEmpty()) {
                        updatedTask.setId(UUID.randomUUID().toString());
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

