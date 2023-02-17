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

    public void updateTask(int id, Task task) {
        List<Task> tasks = taskRepository.getTasks();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDescription(task.getDescription());
                t.setEnd_at(task.getEnd_at());
                t.setState(task.getState());
                t.setTag(task.getTag());
                t.setUpdate_at(task.getUpdate_at());
                t.setSubTask(task.getSubTask());
                taskRepository.saveTask(tasks);
            }
        }
    }

    public void deleteTask(int id) {
        List<Task> tasks = taskRepository.getTasks();
        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                taskRepository.saveTask(tasks);
            }
        }
    }


}
