package org.example.core.entity;

import org.example.core.validation.TaskId;
import org.example.core.state.TaskState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Task {

    private TaskId id;
    private TaskId parentId;
    private Date createdDate;
    private Date dueDate;
    private String content;
    private TaskState state;
    private String tag;
    private List<Task> subTask;

    public Task(){}

    public static Task create(String content, String dueDate, String tag){
        final Task task = new Task();
        task.setContent(content);
        task.setDueDate(new Date(dueDate));
        task.setState(TaskState.TODO);
        task.setSubTask(new ArrayList<>());
        task.setTag(tag);
        return task;
    }

    public Task update(String content, String dueDate, String state, String tag){
        this.setContent(content);
        this.setDueDate(new Date(dueDate));
        this.setState(TaskState.valueOf(state));
        this.setTag(tag);
        return this;
    }

    public Task createSubTask(String content, String dueDate, String tag){
        Task subTask = Task.create(content, dueDate, tag);
        subTask.setParentId(this.getId());
        subTask.setTag(tag);
        this.getSubTask().add(subTask);
        return this;
    }

    public Task copyWith(Task task){
        this.setContent(task.getContent());
        this.setDueDate(task.getDueDate());
        this.setState(task.getState());
        this.setTag(task.getTag());
        return this;
    }

    public TaskId getId() {
        return id;
    }

    public void setId(TaskId id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return  state == task.state && Objects.equals(subTask, task.subTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, subTask);
    }
}
