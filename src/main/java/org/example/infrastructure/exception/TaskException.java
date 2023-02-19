package org.example.infrastructure.exception;

public class TaskException extends Exception {

    TaskException() {
        super();
    }

    public TaskException(String message) {
        super(message);
    }

}
