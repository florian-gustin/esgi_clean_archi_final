package org.example.infrastructure.io.writer;

import org.example.infrastructure.io.writer.FileWriter;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FileWriterTest {

    @Test
    void testWriteFile() {

        FileWriter fileWriter = mock(FileWriter.class);

        verify(fileWriter).write("Hello");

    }

}
