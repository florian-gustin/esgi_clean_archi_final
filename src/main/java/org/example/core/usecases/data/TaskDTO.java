package org.example.core.usecases.data;

import org.example.core.port.UseCaseParams;

import java.util.Objects;

public class TaskDTO implements UseCaseParams {

    public TaskActionType actionType;
    public String taskId;
    public String parentId;
    public String dueDate;
    public String content;
    public String status;
    public String tag;

    public TaskDTO(TaskActionType actionType, String taskId, String parentId, String dueDate, String content, String status, String tag) {
        this.actionType = actionType;
        this.taskId = taskId;
        this.parentId = parentId;
        this.dueDate = dueDate;
        this.content = content;
        this.status = status;
        this.tag = tag;
    }

    public TaskDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDTO taskDTO)) return false;
        return actionType == taskDTO.actionType && Objects.equals(taskId, taskDTO.taskId) && Objects.equals(parentId, taskDTO.parentId) && Objects.equals(dueDate, taskDTO.dueDate) && Objects.equals(content, taskDTO.content) && Objects.equals(status, taskDTO.status) && Objects.equals(tag, taskDTO.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionType, taskId, parentId, dueDate, content, status, tag);
    }
}
