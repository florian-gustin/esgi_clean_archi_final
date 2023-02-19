package org.example.core.usecases;

import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
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
        if(!isDeleted){
            consoleLogger.message("The task has not been deleted", DebugLevel.ERROR);
            debugLogger.message("The task has not been deleted", DebugLevel.ERROR);
        }else{
            consoleLogger.message("The task has been deleted", DebugLevel.OK);
            debugLogger.message("The task has been deleted", DebugLevel.OK);
        }
        return isDeleted;
    }
}
