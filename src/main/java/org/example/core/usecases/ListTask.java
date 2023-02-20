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

import java.time.LocalDate;
import java.util.*;

import org.example.core.usecases.data.TaskState;

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
            Deque<TaskState> filteredTask = filterTask(tasks);
            for( TaskState task : filteredTask) {
                if (task.isOverDue) {
                    String taskPrint = "\u001B[41m" + task.task.toString() + "\u001B[0m";
                    consoleLogger.message(message+" "+taskPrint, DebugLevel.INFO);
                    debugLogger.message(message+" "+task, DebugLevel.INFO);
                } else {
                    consoleLogger.message(message+" "+task.task.toString(), DebugLevel.INFO);
                    debugLogger.message(message+" "+task, DebugLevel.OK);
                }
            }
        }
        // afficher
        return (Void) null;
    }

    private Deque<TaskState> filterTask(Tasks tasks) {
        Deque<TaskState> filteredTask = new LinkedList<>();
        for( Task task : tasks.getData()) {
            if (task.getDueDate() != null && task.getDueDate().getValue().isBefore(LocalDate.now())){
                filteredTask.addFirst(new TaskState(task, true));
            } else {
                filteredTask.add(new TaskState(task, false));
            }
        }
        return filteredTask;
    }
}
