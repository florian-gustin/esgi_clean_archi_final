package org.example.infrastructure.io.logger;

import java.time.LocalDate;

public class DebugLogger implements Logger<String> {

    private String beforeMessage(DebugLevel debug, LocalDate date) {
        String message = "";

        switch (debug) {

            case ERROR -> {
                message = "[err]" + "[" + date + "] ";
            }
            case OK -> {
                message = "[ok]" + "[" + date + "] ";
            }
        }

        return message;

    }

    @Override
    public String message(String value) {
        return  message(value, DebugLevel.OK);
    }

    @Override
    public String message(String value, DebugLevel level) {
        return message(value, level, LocalDate.now());
    }

    private String message(String value, DebugLevel level, LocalDate date) {
        return beforeMessage(level, date) + value;
    }


}
