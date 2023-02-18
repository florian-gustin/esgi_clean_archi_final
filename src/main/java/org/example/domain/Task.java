package org.example.domain;

import java.time.LocalDate;
import java.util.List;

enum TaskState{
    TODO, PENDING, PROGRESS, DONE, CANCELLED, CLOSED
}

public class Task {

    private TaskId id;
    private Date createdDate;
    private Date dueDate;
    private String content;
    private TaskState state;
    private List<Task> subTask;


    private Task(){}

    public static Task create(String content, String dueDate){
        final Task task = new Task();
        task.setContent(content);
        task.setDueDate(new Date(dueDate));
        task.setState(TaskState.TODO);
        return task;
    }


    public void addSubTask(Task task){
        this.subTask.add(task);
    }


    public void removeSubTask(Task task){
        this.subTask.remove(task);
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
