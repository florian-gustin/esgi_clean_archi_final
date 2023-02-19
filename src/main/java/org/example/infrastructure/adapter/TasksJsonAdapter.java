package org.example.infrastructure.adapter;

import com.google.gson.Gson;
import org.example.core.entity.Tasks;

public class TasksJsonAdapter implements Adapter<Tasks> {

    @Override
    public Tasks convertToObject(String json){
        return new Gson().fromJson(json, Tasks.class);
    }

    @Override
    public String convertToString(Tasks object){
        return new Gson().toJson(object);
    }
}
