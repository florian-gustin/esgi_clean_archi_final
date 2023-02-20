package org.example.core.usecases;

import org.example.core.entity.Task;
import org.example.core.port.ObjectMapper;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
import org.example.core.usecases.utils.MessageFormatterUtils;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.infrastructure.io.logger.DebugLevel;
import org.example.infrastructure.io.logger.Logger;

import java.util.Objects;

public class CreateSubTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;
    private final Logger<String> debugLogger;
    private final Logger<Void> consoleLogger;
    private final ObjectMapper<Task, TaskPersistenceObject> objectMapper;

    public CreateSubTask(TaskRepository taskRepository, Logger<String> debugLogger, Logger<Void> consoleLogger, ObjectMapper<Task, TaskPersistenceObject> objectMapper) {
        this.taskRepository = taskRepository;
        this.debugLogger = debugLogger;
        this.consoleLogger = consoleLogger;
        this.objectMapper = objectMapper;
    }


    @Override
    public Void apply(TaskDTO input) {
        Task parentTask = objectMapper.toEntity(taskRepository.getTaskById(input.parentId));
        String message = MessageFormatterUtils.createSubTask(input);
        if(Objects.isNull(parentTask)){
            consoleLogger.message("parent task #"+input.parentId+ "not found", DebugLevel.ERROR);
            debugLogger.message("parent task #"+input.parentId+ "not found", DebugLevel.ERROR);
            return (Void) null;
        }
        parentTask.createSubTask(input.content, input.dueDate, input.tag);
        Task updateTask = objectMapper.toEntity(taskRepository.updateTask(objectMapper.toPersistenceObject(parentTask)));
        if(Objects.isNull(updateTask)) {
            consoleLogger.message(message+ "? not created", DebugLevel.ERROR);
            debugLogger.message(message+ "? created", DebugLevel.ERROR);
        }else{
            consoleLogger.message(message+updateTask.getId().getValue()+ " created", DebugLevel.OK);
            debugLogger.message(message+updateTask.getId().getValue()+ " created", DebugLevel.OK);
        }
        return (Void) null;
    }
}
