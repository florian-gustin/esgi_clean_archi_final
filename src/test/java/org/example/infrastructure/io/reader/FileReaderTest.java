package org.example.infrastructure.io.reader;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FileReaderTest {

    @Test
    void readTest() {

        File file = new File("src/test/resources/FileTest.json");
        FileReader fileReader = new FileReader(file);

        assertEquals("Hello ESGI", fileReader.read());

    }

}
