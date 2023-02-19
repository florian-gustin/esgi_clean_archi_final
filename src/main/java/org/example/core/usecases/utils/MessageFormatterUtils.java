package org.example.core.usecases.utils;

import org.example.core.usecases.data.TaskDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageFormatterUtils {

    private static String withTaskDTO(TaskDTO input){
        List<String> list = new ArrayList<>();
        if(Objects.nonNull(input.taskId)){
            list.add(input.taskId);
        }
        if(Objects.nonNull(input.content)){
            list.add("'"+input.content+"'");
        }
        if(Objects.nonNull(input.status)){
            list.add("'"+input.status+"'");
        }
        if(Objects.nonNull(input.dueDate)){
            list.add("'"+input.dueDate+"'");
        }
        if(Objects.nonNull(input.tag)){
            list.add("'"+input.tag+"'");
        }
        return String.join(" ", list);
    }

    public static String updateTask(TaskDTO input){
        return "update "+ withTaskDTO(input)+" : task ";
    }

    public static String removeTask(TaskDTO input){
        return "remove "+ input.taskId +" : task ";
    }

    public static String createTask(TaskDTO input){
        return "add "+ withTaskDTO(input) +" : task ";
    }

    public static String listTask(){
        return "list all tasks";
    }
}
