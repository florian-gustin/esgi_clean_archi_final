package org.example.infrastructure.data;


import java.util.List;

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
        TaskPersistenceObject copy = new TaskPersistenceObject();
        copy.setId(taskPersistenceObject.getId());
        copy.setParentId(taskPersistenceObject.getParentId());
        copy.setCreatedDate(taskPersistenceObject.getCreatedDate());
        copy.setDueDate(taskPersistenceObject.getDueDate());
        copy.setContent(taskPersistenceObject.getContent());
        copy.setStatus(taskPersistenceObject.getStatus());
        copy.setTag(taskPersistenceObject.getTag());
        copy.setSubTask(taskPersistenceObject.getSubTask());
        return copy;
    }
}
