package org.example.infrastructure.io.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryWriter implements Writer {


    public boolean write(String directory) {
        try {
            Path path = Paths.get(directory);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
