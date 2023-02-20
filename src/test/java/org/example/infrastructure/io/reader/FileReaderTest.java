package org.example.infrastructure.io.reader;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FileReaderTest {

    @Test
    void readTest() {

        FileReader fileReader = new FileReader("src/test/resources/FileTest.json");

        assertEquals("Hello ESGI", fileReader.read());

    }

}
