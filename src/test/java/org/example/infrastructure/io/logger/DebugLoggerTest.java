package org.example.infrastructure.io.logger;

import org.example.infrastructure.io.writer.DummyWriterMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebugLoggerTest {

    LocalDateTime date = LocalDateTime.now();
    DebugLogger logger = new DebugLogger(new DummyWriterMock());


    Method getMessage() {
        try {
            Method method = DebugLogger.class.getDeclaredMethod("message", String.class, DebugLevel.class, LocalDateTime.class);

            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOkMessage() {


        try {
            String output = String.valueOf(getMessage().invoke(logger, "this test work", DebugLevel.OK, date));
            assertEquals("[ok+]["+date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm,ss"))+"] this test work", output);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testErrMessage() {


        try {
            String output = String.valueOf(getMessage().invoke(logger, "a very dangerous test", DebugLevel.ERROR, date));
            assertEquals("[err]["+date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH'h'mm,ss"))+"] a very dangerous test", output);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

}
