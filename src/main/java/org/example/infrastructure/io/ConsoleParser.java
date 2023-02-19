package org.example.infrastructure.io;

import org.example.application.TaskActionType;
import org.example.application.TaskParams;
import org.example.infrastructure.config.ActionArgs;
import org.example.infrastructure.config.ParserConfigConst;
import org.example.kernel.Parser;
import org.example.kernel.Reader;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleParser implements Parser<List<String>> {

    @Override
    public TaskParams parse(List<String> value) {
        String action = value.get(0);
        boolean test = ParserConfigConst.AVAILABLE_ACTIONS.stream().map(ActionArgs::getName).collect(Collectors.toList()).contains(action);
        for ( int i = 0; i < ParserConfigConst.AVAILABLE_ACTIONS.size(); i++){
            if (action.equals(ParserConfigConst.AVAILABLE_ACTIONS.get(i).getName())){
                return new TaskParams(TaskActionType.ADD, value.get(1), value.get(2), value.get(3), value.get(4), value.get(4));
            }
        }

        return null;
    }
}
