package org.example.infrastructure.io.logger;

import java.time.LocalDate;

public class DebugLogger implements Logger<String> {

    private String beforeMessage(DebugLevel debug) {
        String message = "";
        LocalDate date = LocalDate.now();

        switch (debug) {

            case ERROR -> {
                message = "[err]" + "[" + date + "]";
            }
            case OK -> {
                message = "[ok]" + "[" + date + "]";
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
        return beforeMessage(level) + value;
    }

}
