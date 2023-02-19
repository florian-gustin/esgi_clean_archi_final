package org.example.core.usecases.data;

import org.example.core.port.UseCaseParams;

public class TaskDTO implements UseCaseParams {

    public TaskActionType actionType;
    public String taskId;
    public String parentId;
    public String dueDate;
    public String content;
    public String status;
    public String tag;

    public TaskDTO(TaskActionType actionType, String taskId, String dueDate, String content, String status, String tag) {
        this.actionType = actionType;
        this.taskId = taskId;
        this.dueDate = dueDate;
        this.content = content;
        this.status = status;
        this.tag = tag;
    }

    public TaskDTO() {
    }
}
