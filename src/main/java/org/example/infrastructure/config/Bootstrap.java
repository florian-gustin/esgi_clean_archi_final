package org.example.infrastructure.config;

import org.example.core.port.ObjectMapper;
import org.example.infrastructure.mapper.TaskMapper;
import org.example.infrastructure.data.TaskPersistenceObject;
import org.example.core.entity.Task;
import org.example.core.usecases.*;
import org.example.core.usecases.data.TaskDTO;
import org.example.core.port.TaskRepository;
import org.example.infrastructure.adapter.TasksJsonAdapter;
import org.example.infrastructure.io.logger.ConsoleLogger;
import org.example.infrastructure.io.logger.DebugLogger;
import org.example.infrastructure.io.parser.cli.ConsoleParser;
import org.example.infrastructure.io.reader.FileReader;
import org.example.infrastructure.io.writer.DirectoryWriter;
import org.example.infrastructure.io.writer.FileWriter;
import org.example.infrastructure.repository.FileTaskRepository;
import org.example.infrastructure.io.logger.Logger;
import org.example.infrastructure.io.parser.Parser;
import org.example.infrastructure.io.reader.Reader;
import org.example.infrastructure.io.writer.Writer;

import java.io.File;
import java.io.IOException;

public class Bootstrap {

    private final Parser<TaskDTO> parser = new ConsoleParser();
    private final Logger<Void> consoleLogger = new ConsoleLogger();
    private final Reader<String> fileReader = new FileReader(new File(Constants.DIRECTORY_PATH+Constants.DATA_FILENAME));
    private final Writer fileWriter = new FileWriter(Constants.DIRECTORY_PATH+Constants.DATA_FILENAME);
    private final Writer fileLogWriter = new FileWriter(Constants.DIRECTORY_PATH+Constants.LOG_FILENAME);
    private final Logger<String> debugLogger =new DebugLogger(fileLogWriter);
    private final Writer directoryWriter = new DirectoryWriter();
    private final TasksJsonAdapter tasksJsonAdapter = new TasksJsonAdapter();
    private final ObjectMapper<Task, TaskPersistenceObject> objectMapper = new TaskMapper();
    private final TaskRepository taskRepository = new FileTaskRepository(tasksJsonAdapter, fileWriter, directoryWriter, fileReader);
    private final CreateTask createTask = new CreateTask(taskRepository, debugLogger, consoleLogger, objectMapper);
    private final CreateSubTask createSubTask = new CreateSubTask(taskRepository, debugLogger, consoleLogger, objectMapper);
    private final UpdateTask updateTask = new UpdateTask(taskRepository, debugLogger, consoleLogger, objectMapper);
    private final RemoveTask removeTask = new RemoveTask(taskRepository, debugLogger, consoleLogger);
    private final ListTask listTask = new ListTask(taskRepository, debugLogger, consoleLogger, objectMapper);

    public Bootstrap() throws IOException {
    }

    public CreateSubTask getCreateSubTask() {
        return createSubTask;
    }

    public Logger<String> getDebugLogger() {
        return debugLogger;
    }

    public Writer getDirectoryWriter() {
        return directoryWriter;
    }

    public ListTask getListTask() {
        return listTask;
    }

    public Parser<TaskDTO> getParser() {
        return parser;
    }

    public Logger<Void> getConsoleLogger() {
        return consoleLogger;
    }

    public Reader<String> getFileReader() {
        return fileReader;
    }

    public Writer getFileWriter() {
        return fileWriter;
    }

    public TasksJsonAdapter getTasksJsonAdapter() {
        return tasksJsonAdapter;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public CreateTask getCreateTask() {
        return createTask;
    }

    public UpdateTask getUpdateTask() {
        return updateTask;
    }

    public RemoveTask getRemoveTask() {
        return removeTask;
    }
}
