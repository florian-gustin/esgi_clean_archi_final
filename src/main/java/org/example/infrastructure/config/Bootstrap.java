package org.example.infrastructure.config;

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
    private final Logger<String> debugLogger =new DebugLogger();
    private final Reader<String> fileReader = new FileReader(new File(Constants.FILENAME));
    private final Writer fileWriter = new FileWriter(Constants.FILENAME);
    private final Writer directoryWriter = new DirectoryWriter();
    private final TasksJsonAdapter tasksJsonAdapter = new TasksJsonAdapter();
    private final TaskRepository taskRepository = new FileTaskRepository(tasksJsonAdapter, fileWriter, directoryWriter, fileReader);
    private final CreateTask createTask = new CreateTask(taskRepository);
    private final CreateSubTask createSubTask = new CreateSubTask(taskRepository);
    private final UpdateTask updateTask = new UpdateTask(taskRepository);
    private final RemoveTask removeTask = new RemoveTask(taskRepository);
    private final ListTask listTask = new ListTask(taskRepository);

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