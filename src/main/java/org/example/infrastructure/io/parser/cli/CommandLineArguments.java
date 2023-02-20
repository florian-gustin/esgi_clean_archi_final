package org.example.infrastructure.io.parser.cli;

import org.example.core.usecases.data.TaskActionType;

import java.util.Arrays;
import java.util.List;

public class CommandLineArguments {
    public  static List<ActionArgs> AVAILABLE_ACTIONS  = Arrays.asList(
            new ActionArgs("add", "add a task", false, TaskActionType.ADD),
            new ActionArgs("add-sub", "add a subtask", true, TaskActionType.ADDSUBTASK),
            new ActionArgs("list", "list all tasks", false, TaskActionType.LIST),
            new ActionArgs("remove", "remove a task by id", true, TaskActionType.REMOVE),
            new ActionArgs("update", "update a task by id", true, TaskActionType.UPDATE)
    );


    public  static List<OptionArgs> AVAILABLE_OPTIONS  = Arrays.asList(
            new OptionArgs("-c", "content of a task", true),
            new OptionArgs("-d", "due date of a task", true),
            new OptionArgs("-s", "status of a task", true),
            new OptionArgs("-t", "tag of a task", true)
    );
}
