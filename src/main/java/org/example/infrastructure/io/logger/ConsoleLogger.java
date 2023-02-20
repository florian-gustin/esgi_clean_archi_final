package org.example.infrastructure.io.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger<Void> {
    @Override
    public Void message(String value) {
        message(value, DebugLevel.INFO);
        return null;
    }

    @Override
    public Void message(String value, DebugLevel level) {
        String message = "";
        LocalDateTime date = LocalDateTime.now();

        switch (level) {

            case ERROR -> {
                message = "[err]" + "[" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm,ss")) + "] ";
            }
            case OK -> {
                message = "[ok+]" + "[" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm,ss")) + "] ";
            }
        }

        System.out.println(message + value);
        return null;
    }
}
