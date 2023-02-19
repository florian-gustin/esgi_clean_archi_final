package org.example.application;

import org.example.kernel.UseCaseParams;

public class TaskParams implements UseCaseParams {

    public TaskActionType actionType;
    public String taskId;
    public String dueDate;
    public String content;
    public String status;
    public String tag;

    public TaskParams(TaskActionType actionType, String taskId, String dueDate, String content, String status, String tag) {
        this.actionType = actionType;
        this.taskId = taskId;
        this.dueDate = dueDate;
        this.content = content;
        this.status = status;
        this.tag = tag;
    }
}
