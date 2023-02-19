package org.example.core.usecases;

import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
import org.example.core.usecases.utils.MessageFormatterUtils;
import org.example.infrastructure.io.logger.DebugLevel;
import org.example.infrastructure.io.logger.Logger;

public class RemoveTask implements UseCase<TaskDTO, Boolean> {
    private final TaskRepository taskRepository;
    private final Logger<String> debugLogger;
    private final Logger<Void> consoleLogger;

    public RemoveTask(TaskRepository taskRepository, Logger<String> debugLogger, Logger<Void> consoleLogger) {
        this.taskRepository = taskRepository;
        this.debugLogger = debugLogger;
        this.consoleLogger = consoleLogger;
    }

    @Override
    public Boolean apply(TaskDTO input) {
        boolean isDeleted = taskRepository.removeTaskById(input.taskId);
        String message = MessageFormatterUtils.removeTask(input.taskId);
        if(!isDeleted){
            consoleLogger.message(message+"has not been deleted", DebugLevel.ERROR);
            debugLogger.message(message+"has not been deleted", DebugLevel.ERROR);
        }else{
            consoleLogger.message(message+"has been deleted", DebugLevel.OK);
            debugLogger.message(message+"has been deleted", DebugLevel.OK);
        }
        return isDeleted;
    }
}
