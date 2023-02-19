package org.example.infrastructure.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserConfigConst {

    public  static List<ActionArgs> AVAILABLE_ACTIONS  = Arrays.asList(
            new ActionArgs("add", "add a task", false),
            new ActionArgs("list", "list all tasks", false),
            new ActionArgs("remove", "remove a task by id", true),
            new ActionArgs("update", "update a task by id", true)
    );


    public  static List<OptionArgs> AVAILABLE_OPTIONS  = Arrays.asList(
            new OptionArgs("-c", "content of a task", true),
            new OptionArgs("-d", "due date of a task", true),
            new OptionArgs("-s", "status of a task", true),
            new OptionArgs("-t", "tag of a task", true)
    );
}
