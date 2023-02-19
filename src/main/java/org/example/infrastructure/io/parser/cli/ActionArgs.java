package org.example.infrastructure.io.parser.cli;

import org.example.core.usecases.data.TaskActionType;

public class ActionArgs {
    private final String name;
    private final String description;
    private final boolean needValue;
    private final TaskActionType actionType;

    public ActionArgs(String name, String description, boolean needValue, TaskActionType actionType) {
        this.name = name;
        this.description = description;
        this.needValue = needValue;
        this.actionType = actionType;

    }

    public TaskActionType getActionType(){
        return actionType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNeedValue() {
        return needValue;
    }
}
