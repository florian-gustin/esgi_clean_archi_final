package org.example.infrastructure.io.writer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FileWriterTest {

    final static String FILENAME = "HelloWorld.txt";
    final static String CONTENT = "Hello Esgiens !!!!!!!";

    static void delete() {
        File file = new File(FILENAME);
        if(file.exists()) {
            file.delete();
        }
    }

    @BeforeAll
    static void setup() {
        delete();
    }

    @AfterAll
    static void dispose() {
        delete();
    }

    @Test
    void testWriteFile() {

        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            assertTrue(fileWriter.write(CONTENT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testReadWroteFile() {
        try {
            String data = "";
            Scanner scanner = new Scanner(new File(FILENAME));
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
            }
            scanner.close();
            assertEquals(CONTENT, data);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
