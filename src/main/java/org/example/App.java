package org.example;

import org.example.infrastructure.config.Bootstrap;
import org.example.core.usecases.data.TaskDTO;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        //DI
        final Bootstrap config = new Bootstrap();

        //User inputs from command line
        final TaskDTO params = config
                .getParser()
                .parse(Arrays.stream(args).toList());

        // Apply use cases
        switch (params.actionType){
            case ADD -> config.getCreateTask().apply(params);
            case ADDSUBTASK -> config.getCreateSubTask().apply(params);
            case REMOVE -> config.getRemoveTask().apply(params);
            case UPDATE -> config.getUpdateTask().apply(params);
            case LIST -> config.getListTask().apply(params);
        }
    }
}
