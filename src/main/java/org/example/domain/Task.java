package org.example.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



public class Task {

    private TaskId id;
    private TaskId parentId;
    private LocalDate createdDate;
    private LocalDate dueDate;
    private LocalDate closedDate;
    private String content;
    private TaskState state;
    private String tag;
    private List<Task> subTask;

    private Task(){}

    public static Task create(String content, String dueDate){
        final Task task = new Task();
        task.setContent(content);
        task.setDueDate(LocalDate.parse(dueDate));
        task.setState(TaskState.TODO);
        return task;
    }

    public TaskId getId() {
        return id;
    }

    public void setId(TaskId id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Task> getSubTask() {
        return subTask;
    }

    public void setSubTask(List<Task> subTask) {
        this.subTask = subTask;
    }

    public TaskId getParentId() {
        return parentId;
    }

    public void setParentId(TaskId parentId) {
        this.parentId = parentId;
    }
}
