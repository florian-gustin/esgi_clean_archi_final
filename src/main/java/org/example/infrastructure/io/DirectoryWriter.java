package org.example.infrastructure.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryWriter {

    public static boolean mkdir(String directory) {
        try {
            Files.createDirectory(Paths.get(directory));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
