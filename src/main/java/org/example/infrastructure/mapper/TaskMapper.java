package org.example.infrastructure.mapper;

import org.example.core.entity.Task;
import org.example.core.port.ObjectMapper;
import org.example.core.state.TaskState;
import org.example.core.validation.SimpleDate;
import org.example.core.validation.TaskId;
import org.example.infrastructure.data.TaskPersistenceObject;

import java.util.Objects;

public class TaskMapper implements ObjectMapper<Task, TaskPersistenceObject> {

    @Override
    public TaskPersistenceObject toPersistenceObject(Task entity) {
        TaskPersistenceObject object = new TaskPersistenceObject();
        object.setId(Objects.nonNull(entity.getId()) ? entity.getId().getValue() : null);
        object.setParentId(Objects.nonNull(entity.getParentId()) ? entity.getParentId().getValue() : null);
        object.setCreatedDate(entity.getCreatedDate().getValue().toString());
        object.setDueDate(Objects.nonNull(entity.getDueDate()) ? entity.getDueDate().getValue().toString() : null);
        object.setContent(entity.getContent());
        object.setStatus(entity.getState().name());
        object.setTag(entity.getTag());
        object.setSubTask(Objects.nonNull(entity.getSubTask()) ? entity.getSubTask().stream().map(this::toPersistenceObject).toList() : null);
        return object;
    }

    @Override
    public Task toEntity(TaskPersistenceObject persistenceObject) {
        Task task = new Task();
        task.setId(new TaskId(persistenceObject.getId()));
        task.setParentId(Objects.nonNull(persistenceObject.getParentId()) ? new TaskId(persistenceObject.getParentId()) : null);
        task.setCreatedDate(Objects.nonNull(persistenceObject.getCreatedDate()) ? new SimpleDate(persistenceObject.getCreatedDate()) : null);
        task.setDueDate(Objects.nonNull(persistenceObject.getDueDate()) ? new SimpleDate(persistenceObject.getDueDate()) : null);
        task.setContent(persistenceObject.getContent());
        task.setState(TaskState.valueOf(persistenceObject.getStatus()));
        task.setTag(persistenceObject.getTag());
        task.setSubTask(Objects.nonNull(persistenceObject.getSubTask()) ? persistenceObject.getSubTask().stream().map(this::toEntity).toList() : null);
        return task;
    }

}
