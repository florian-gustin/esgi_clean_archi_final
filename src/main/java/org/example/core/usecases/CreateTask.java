package org.example.core.usecases;

import org.example.core.entity.Task;
import org.example.core.port.TaskRepository;
import org.example.core.port.UseCase;
import org.example.core.usecases.data.TaskDTO;
import org.example.infrastructure.io.logger.DebugLevel;
import org.example.infrastructure.io.logger.Logger;

import java.util.Objects;

public class CreateTask implements UseCase<TaskDTO, Void> {

    private final TaskRepository taskRepository;
    private final Logger<String> debugLogger;
    private final Logger<Void> consoleLogger;

    public CreateTask(TaskRepository taskRepository, Logger<String> debugLogger, Logger<Void> consoleLogger) {
        this.taskRepository = taskRepository;
        this.debugLogger = debugLogger;
        this.consoleLogger = consoleLogger;
    }


    @Override
    public Void apply(TaskDTO input) {
        Task createdTask = taskRepository.addTask(Task.create(input.content, input.dueDate, input.tag));

        if(Objects.isNull(createdTask)) {
            consoleLogger.message("Task not created", DebugLevel.ERROR);
            debugLogger.message("Task not created", DebugLevel.ERROR);
        }else{
            consoleLogger.message("Task created", DebugLevel.OK);
            debugLogger.message("Task created", DebugLevel.OK);
        }
        return (Void) null;
    }
}
