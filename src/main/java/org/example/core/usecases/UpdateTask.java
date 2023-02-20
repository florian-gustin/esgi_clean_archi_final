package org.example.core.usecases;

import org.example.core.port.ObjectMapper;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.core.entity.Task;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
import org.example.core.usecases.utils.MessageFormatterUtils;
import org.example.infrastructure.io.logger.DebugLevel;
import org.example.infrastructure.io.logger.Logger;

import java.util.Objects;

public class UpdateTask implements UseCase<TaskDTO, Task> {

    private final TaskRepository taskRepository;
    private final Logger<String> debugLogger;
    private final Logger<Void> consoleLogger;
    private final ObjectMapper<Task, TaskPersistenceObject> objectMapper;

    public UpdateTask(TaskRepository taskRepository, Logger<String> debugLogger, Logger<Void> consoleLogger, ObjectMapper<Task, TaskPersistenceObject> objectMapper) {
        this.taskRepository = taskRepository;
        this.debugLogger = debugLogger;
        this.consoleLogger = consoleLogger;
        this.objectMapper = objectMapper;
    }

    @Override
    public Task apply(TaskDTO input) {
        String updateStr = MessageFormatterUtils.updateTask(input);
        Task task = objectMapper.toEntity(taskRepository.getTaskById(input.taskId));
        if(Objects.isNull(task)){
            consoleLogger.message(updateStr+"does not exist", DebugLevel.ERROR);
            debugLogger.message(updateStr+"does not exist", DebugLevel.ERROR);
            return null;
        }
        task.update(input.content, input.dueDate, input.status, input.tag);
        Task updateTask = objectMapper.toEntity(taskRepository.updateTask(objectMapper.toPersistenceObject(task)));
        if(Objects.isNull(updateTask)){
            consoleLogger.message(updateStr+"has not been updated "+input, DebugLevel.ERROR);
            debugLogger.message(updateStr+"has not been updated "+input, DebugLevel.ERROR);
            return null;
        }
        consoleLogger.message(updateStr+"has been updated "+updateTask, DebugLevel.OK);
        debugLogger.message(updateStr+"has been updated "+updateTask, DebugLevel.OK);
        return updateTask;
    }

}
