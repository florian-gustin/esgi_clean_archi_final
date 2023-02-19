package org.example.infrastructure.adapter;

import com.google.gson.Gson;
import org.example.infrastructure.data.TasksPersistenceObject;

public class TasksJsonAdapter implements Adapter<TasksPersistenceObject> {

    @Override
    public TasksPersistenceObject convertToObject(String json){
        return new Gson().fromJson(json, TasksPersistenceObject.class);
    }

    @Override
    public String convertToString(TasksPersistenceObject object){
        return new Gson().toJson(object);
    }
}
