package org.example.domain;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Date;

public class TaskAdapter extends TypeAdapter<Task> {

    @Override
    public void write(JsonWriter jsonWriter, Task task) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("id").value(String.valueOf(task.getId()));
        jsonWriter.name("created_at").value(String.valueOf(task.getCreated_at()));
        jsonWriter.name("due_date").value(String.valueOf(task.getEnd_at()));
        jsonWriter.name("update_at").value(String.valueOf(task.getUpdate_at()));
        jsonWriter.name("description").value(task.getDescription());
        jsonWriter.name("state").value(task.getState());
        jsonWriter.name("tag").value(task.getTag());
        jsonWriter.name("subtasks").value(task.getSubTask().toString()); // TODO : a verifier

        jsonWriter.endObject();
    }

    @Override
    public Task read(JsonReader jsonReader) throws IOException {
        Task task = new Task();

        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "id":
                    task.setId(jsonReader.nextInt());
                    break;
                case "created_at":
                    task.setCreated_at(Date.valueOf(jsonReader.nextString()));
                    break;
                case "due_date":
                    task.setEnd_at(Date.valueOf(jsonReader.nextString()));
                    break;
                case "update_at":
                    task.setUpdate_at(Date.valueOf(jsonReader.nextString()));
                    break;
                case "description":
                    task.setDescription(jsonReader.nextString());
                    break;
                case "state":
                    task.setState(jsonReader.nextInt());
                    break;
                case "tag":
                    task.setTag(jsonReader.nextString());
                    break;
                case "subtasks":
                    JsonReader subtasks =
                    task.setSubTask(jsonReader.ne); // je pense qu'il faut que la methode renvoi une liste de task comme ca tu la rappelle ici
                    break;
            }

            return task;
        }


    }
}
