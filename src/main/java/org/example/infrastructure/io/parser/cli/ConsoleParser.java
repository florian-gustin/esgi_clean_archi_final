package org.example.infrastructure.io.parser.cli;

import org.example.core.usecases.data.TaskActionType;
import org.example.core.usecases.data.TaskDTO;
import org.example.infrastructure.io.parser.Parser;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleParser implements Parser<TaskDTO> {

    @Override
    public TaskDTO parse(List<String> args) {
        ArrayList<String> argsList = new ArrayList<>(args);
        if (argsList.isEmpty()) {
            throw new IllegalArgumentException("No arguments provided !");
        }
        ActionArgs action = parseAction(argsList);
        TaskDTO taskParams = buildTaskParamsWithAction(action, argsList);
        argsList.remove(0);
        if (action.isNeedValue()) {
            argsList.remove(0);
        }


        return parseOptions(argsList, taskParams);
    }

    private TaskDTO buildTaskParamsWithAction(ActionArgs action, List<String> args) {
        TaskDTO taskParams = new TaskDTO();
        taskParams.actionType = action.getActionType();
        if (action.isNeedValue()) {
            if (isOutOfRange(args.size(), 1)) {
                throw new IllegalArgumentException("Missing value for action " + action.getName());
            }
            setActionValue(taskParams, args.get(1), action);
        }
        return taskParams;
    }

    private TaskDTO setActionValue(TaskDTO taskParams, String value, ActionArgs action){
        switch (action.getActionType()){
            case ADDSUBTASK -> taskParams.parentId = value;
            case REMOVE, UPDATE -> taskParams.taskId = value;
        }
        return taskParams;
    }

    // add a method to fill correctly the dto

    private ActionArgs parseAction(List<String> args) {
        String actionArg = args.get(0);
        Optional<ActionArgs> actionArgsOptional = CommandLineArguments.AVAILABLE_ACTIONS.stream()
                .filter(e -> e.getName().equals(actionArg))
                .findFirst();
        if (actionArgsOptional.isEmpty()) {
            throw new IllegalArgumentException(actionArg + " is not an existing action !");
        }
        return actionArgsOptional.get();
    }

    private TaskDTO parseOptions(List<String> args, TaskDTO taskParams) {
        for (int i = 0; i < args.size(); i++) {
            OptionArgs option = parseOption(args.get(i));
            if (option.isNeedValue()) {
                i++;
                if (isOutOfRange(args.size(), i)) {
                    throw new IllegalArgumentException("Missing value for option " + option.getName());
                }
                switch (option.getName()) {
                    case "-c" -> taskParams.content = args.get(i);
                    case "-d" -> taskParams.dueDate = args.get(i);
                    case "-s" -> taskParams.status = args.get(i);
                    case "-t" -> taskParams.tag = args.get(i);
                }
            }
        }
        return taskParams;
    }

    private OptionArgs parseOption(String option) {
        Optional<OptionArgs> optionalActionArgs = CommandLineArguments.AVAILABLE_OPTIONS.stream()
                .filter(e -> e.getName().equals(option))
                .findFirst();
        if (optionalActionArgs.isEmpty()) {
            throw new IllegalArgumentException( option + " is not an existing action !");
        }
        return optionalActionArgs.get();
    }

    private boolean isOutOfRange(int listSize, int valueToCheck){
        final ValueRange range = ValueRange.of(0, listSize-1);
        return !range.isValidIntValue(valueToCheck);
    }
}
