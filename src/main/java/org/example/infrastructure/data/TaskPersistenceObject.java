package org.example.infrastructure.data;


import java.util.List;
import java.util.Objects;

public class TaskPersistenceObject {
    private String id;
    private String parentId;
    private String createdDate;
    private String dueDate;
    private String content;
    private String status;
    private String tag;
    private List<TaskPersistenceObject> subTask;

    public void setSubTask(List<TaskPersistenceObject> subTask) {
        this.subTask = subTask;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getTag() {
        return tag;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<TaskPersistenceObject> getSubTask() {
        return subTask;
    }

    public TaskPersistenceObject copyWith(TaskPersistenceObject taskPersistenceObject) {
        this.setId(taskPersistenceObject.getId());
        this.setParentId(taskPersistenceObject.getParentId());
        this.setCreatedDate(taskPersistenceObject.getCreatedDate());
        this.setDueDate(taskPersistenceObject.getDueDate());
        this.setContent(taskPersistenceObject.getContent());
        this.setStatus(taskPersistenceObject.getStatus());
        this.setTag(taskPersistenceObject.getTag());
        this.setSubTask(taskPersistenceObject.getSubTask());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskPersistenceObject that = (TaskPersistenceObject) o;
        return Objects.equals(id, that.id) && Objects.equals(parentId, that.parentId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(dueDate, that.dueDate) && Objects.equals(content, that.content) && Objects.equals(status, that.status) && Objects.equals(tag, that.tag) && Objects.equals(subTask, that.subTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, createdDate, dueDate, content, status, tag, subTask);
    }
}
