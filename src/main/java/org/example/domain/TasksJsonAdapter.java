package org.example.domain;

import com.google.gson.Gson;
import org.example.domain.interfaces.Adapter;

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
