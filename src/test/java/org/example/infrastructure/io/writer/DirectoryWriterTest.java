package org.example.infrastructure.io.writer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryWriterTest {

    final static String FILENAME = "myFolderTest";

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
    void mkdirTest() {
        DirectoryWriter directoryWriter = new DirectoryWriter();

        assertTrue(directoryWriter.write(FILENAME));

    }

}
