package org.example.core.usecases;

import org.example.core.port.ObjectMapper;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.core.entity.Task;
import org.example.core.entity.Tasks;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
import org.example.core.usecases.utils.MessageFormatterUtils;
import org.example.infrastructure.io.logger.DebugLevel;
import org.example.infrastructure.io.logger.Logger;

public class ListTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;
    private final Logger<String> debugLogger;
    private final Logger<Void> consoleLogger;
    private final ObjectMapper<Task, TaskPersistenceObject> objectMapper;


    public ListTask(TaskRepository taskRepository, Logger<String> debugLogger, Logger<Void> consoleLogger, ObjectMapper<Task, TaskPersistenceObject> objectMapper) {
        this.taskRepository = taskRepository;
        this.debugLogger = debugLogger;
        this.consoleLogger = consoleLogger;
        this.objectMapper = objectMapper;
    }


    @Override
    public Void apply(TaskDTO input) {
        final Tasks tasks = new Tasks(taskRepository.getAll().getData().stream().map(objectMapper::toEntity).toList());
        String message = MessageFormatterUtils.listTask();
        if(tasks.getData().isEmpty()){
            consoleLogger.message(message+"none tasks", DebugLevel.ERROR);
            debugLogger.message(message+"none tasks", DebugLevel.ERROR);
        }else{
            consoleLogger.message(message+" "+tasks, DebugLevel.OK);
            debugLogger.message(message+" "+tasks, DebugLevel.OK);
        }
        // afficher
        return (Void) null;
    }
}
