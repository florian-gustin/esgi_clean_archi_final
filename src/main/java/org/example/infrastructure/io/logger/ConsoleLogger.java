package org.example.infrastructure.io.logger;

public class ConsoleLogger implements Logger<Void> {
    @Override
    public Void message(String value) {
        message(value, DebugLevel.INFO);
        return null;
    }

    @Override
    public Void message(String value, DebugLevel level) {
        if(level == DebugLevel.INFO) {
            System.out.println(value);
        }

        return null;
    }
}
