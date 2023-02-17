package org.example.domain;

import org.example.domain.interfaces.ITaskService;
import org.example.domain.interfaces.TaskRepository;

import java.util.List;

public class TaskService implements ITaskService {

    private TaskRepository taskRepository;


    public void createTask(Task task) {
        List<Task> tasks = taskRepository.getTasks();
        tasks.add(task);
        taskRepository.saveTask(tasks);
    }

    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    public void updateTask(int id, Task taskToUpdate) {
        List<Task> tasks = taskRepository.getTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(taskToUpdate.getDescription());
                task.setEnd_at(taskToUpdate.getEnd_at());
                task.setState(taskToUpdate.getState());
                task.setTag(taskToUpdate.getTag());
                task.setUpdate_at(taskToUpdate.getUpdate_at());
                task.setSubTask(taskToUpdate.getSubTask());
                taskRepository.saveTask(tasks);
                return;
            }
        }
        throw new RuntimeException("Task not found");
    }

    public void deleteTask(int id) {
        List<Task> tasks = taskRepository.getTasks();
        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                taskRepository.saveTask(tasks);
                return;
            }
        }
        throw new RuntimeException("Task not found");
    }


}
