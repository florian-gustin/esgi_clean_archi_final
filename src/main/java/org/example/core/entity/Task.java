package org.example.core.entity;

import org.example.core.validation.SimpleDate;
import org.example.core.validation.TaskId;
import org.example.core.state.TaskState;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Task {

    private TaskId id;
    private TaskId parentId;
    private SimpleDate createdDate;
    private SimpleDate dueDate;
    private String content;
    private TaskState state;
    private String tag;
    private List<Task> subTask;

    public Task(){}

    public static Task create(String content, String dueDate, String tag){
        final Task task = new Task();
        if (Objects.nonNull(content))
            task.setContent(content);
        if(Objects.nonNull(dueDate))
            task.setDueDate(new SimpleDate(dueDate));
        if(Objects.nonNull(tag))
            task.setTag(tag);
        task.setCreatedDate(new SimpleDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        task.setState(TaskState.TODO);
        task.setSubTask(new ArrayList<>());
        return task;
    }

    public Task update(String content, String dueDate, String state, String tag){
        this.setContent(content);
        this.setDueDate(new SimpleDate(dueDate));
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


    public SimpleDate getCreatedDate() {
        return createdDate;
    }

    public SimpleDate getDueDate() {
        return dueDate;
    }

    public void setCreatedDate(SimpleDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setDueDate(SimpleDate dueDate) {
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
