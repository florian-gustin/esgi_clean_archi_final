package org.example.infrastructure.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.infrastructure.data.TasksPersistenceObject;

import java.util.List;

public class TasksJsonAdapter implements Adapter<TasksPersistenceObject> {
    private final Gson gson;
    public TasksJsonAdapter() {
       gson = new GsonBuilder().serializeNulls().create();
    }
    @Override
    public TasksPersistenceObject convertToObject(String json){
        return gson.fromJson(json, TasksPersistenceObject.class);
    }

    @Override
    public String convertToString(TasksPersistenceObject object){
        return gson.toJson(object);
    }
}
