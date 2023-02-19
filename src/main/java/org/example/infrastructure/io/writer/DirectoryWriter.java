package org.example.infrastructure.io.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryWriter implements Writer {


    public void write(String directory) {
        try {
            Files.createDirectory(Paths.get(directory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
