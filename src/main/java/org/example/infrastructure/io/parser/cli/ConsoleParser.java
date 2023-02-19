package org.example.infrastructure.io.parser.cli;

import org.example.infrastructure.io.parser.Parser;
import org.example.infrastructure.io.parser.cli.ActionArgs;
import org.example.infrastructure.io.parser.cli.CommandLineArguments;
import org.example.core.usecases.data.TaskActionType;
import org.example.core.usecases.data.TaskDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsoleParser implements Parser<TaskDTO> {

    @Override
    public TaskDTO parse(List<String> value) {
        String actionArgs = value.get(0);
        ActionArgs action = parseAction(actionArgs);
        TaskDTO taskParams = new TaskDTO();
        taskParams.actionType = action.getActionType();
        value.remove(0);

        return  taskParams;
    }

    private ActionArgs parseAction(String actionArg){
        Optional<ActionArgs> actionArgsOptional = CommandLineArguments.AVAILABLE_ACTIONS.stream()
                .filter(e -> e.getName().equals(actionArg))
                .findFirst();
        if (actionArgsOptional.isEmpty()){
            throw new IllegalArgumentException(actionArg + " is not an existing action !");
        }
        return actionArgsOptional.get();
    }
}
