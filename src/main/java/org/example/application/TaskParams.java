package org.example.application;

import org.example.kernel.UseCaseParams;

public class TaskParams implements UseCaseParams {
    public String user;
    public String listAllFromUser;

    public boolean listAll;
    public String taskId;
    public String dueDate;
    public String content;
    public String status;
}
