package org.example.infrastructure.io.reader;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
public class FileReaderTest {

    @Test
    void readTest() {

        FileReader fileReader = mock(FileReader.class);

        verify(fileReader).read();

    }

}
