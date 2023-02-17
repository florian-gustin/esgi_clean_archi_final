package org.example.infrastructure;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.domain.Task;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class TaskAdapter extends TypeAdapter<Task> {

    private final Gson gson;

    TaskAdapter(Gson gson) {
        this.gson = gson;
    }

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
            String value = jsonReader.nextName();
            if (value.equals("id")) {
                task.setId(jsonReader.nextInt());
            } else if (value.equals("created_at")) {
                task.setCreated_at(Date.valueOf(jsonReader.nextString()));
            } else if (value.equals("due_date")) {
                task.setEnd_at(Date.valueOf(jsonReader.nextString()));
            } else if (value.equals("update_at")) {
                task.setUpdate_at(Date.valueOf(jsonReader.nextString()));
            } else if (value.equals("description")) {
                task.setDescription(jsonReader.nextString());
            } else if (value.equals("state")) {
                task.setState(jsonReader.nextInt());
            } else if (value.equals("tag")) {
                task.setTag(jsonReader.nextString());
            } else if (value.equals("subtasks")) {
                task.setSubTask(gson.getAdapter(List.class).read(jsonReader));
            } else {
                jsonReader.skipValue();
            }
            jsonReader.endObject();
        }
        return task;
    }
}
