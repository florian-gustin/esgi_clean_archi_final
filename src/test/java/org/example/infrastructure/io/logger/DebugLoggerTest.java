package org.example.infrastructure.io.logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebugLoggerTest {

    LocalDate date = LocalDate.of(2009, 2, 19);

    Method getMessage() {
        try {
            Method method = DebugLogger.class.getDeclaredMethod("message", String.class, DebugLevel.class, LocalDate.class);

            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOkMessage() {

        DebugLogger logger = new DebugLogger();

        try {
            String output = String.valueOf(getMessage().invoke(logger, "this test work", DebugLevel.OK, date));
            assertEquals("[ok][2009-02-19] this test work", output);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testErrMessage() {

        DebugLogger logger = new DebugLogger();

        try {
            String output = String.valueOf(getMessage().invoke(logger, "a very dangerous test", DebugLevel.ERROR, date));
            assertEquals("[err][2009-02-19] a very dangerous test", output);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

}
