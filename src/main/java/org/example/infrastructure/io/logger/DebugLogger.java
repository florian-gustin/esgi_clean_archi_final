package org.example.infrastructure.io.logger;

import org.example.infrastructure.io.writer.FileWriter;
import org.example.infrastructure.io.writer.Writer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DebugLogger implements Logger<String> {

    private final Writer fileWriter;

    public DebugLogger(Writer fileWriter) {
        this.fileWriter = fileWriter;
    }

    private String beforeMessage(DebugLevel debug, LocalDateTime date) {
        String message = "";

        switch (debug) {

            case ERROR -> {
                message = "[err]" + "[" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm,ss")) + "] ";
            }
            case OK -> {
                message = "[ok+]" + "[" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm,ss")) + "] ";
            }
        }

        return message;

    }

    @Override
    public String message(String value) {
        return  message(value, DebugLevel.OK, LocalDateTime.now());
    }

    @Override
    public String message(String value, DebugLevel level) {
        return message(value, level, LocalDateTime.now());
    }

    private String message(String value, DebugLevel level, LocalDateTime date) {
        String message = beforeMessage(level, date) + value;
        fileWriter.write(message);
        return message;
    }


}
