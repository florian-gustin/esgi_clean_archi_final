package org.example.core.usecases;

import org.example.core.entity.Tasks;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
import org.example.infrastructure.io.logger.DebugLevel;
import org.example.infrastructure.io.logger.Logger;

import java.util.Objects;

public class ListTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;
    private final Logger<String> debugLogger;
    private final Logger<Void> consoleLogger;

    public ListTask(TaskRepository taskRepository, Logger<String> debugLogger, Logger<Void> consoleLogger) {
        this.taskRepository = taskRepository;
        this.debugLogger = debugLogger;
        this.consoleLogger = consoleLogger;
    }


    @Override
    public Void apply(TaskDTO input) {
        final Tasks tasks = taskRepository.getAll();
        if(Objects.isNull(tasks)){
            consoleLogger.message("None tasks", DebugLevel.ERROR);
            debugLogger.message("None tasks", DebugLevel.ERROR);
        }else{
            consoleLogger.message("Tasks retrieved", DebugLevel.OK);
            debugLogger.message("Tasks retrieved", DebugLevel.OK);
        }
        // afficher
        return (Void) null;
    }
}
