package org.example.infrastructure.io.writer;

import java.io.IOException;

public class FileWriter implements Writer {

    private final java.io.FileWriter writer;

    public FileWriter(String fileName) throws IOException {
        this.writer = new java.io.FileWriter(fileName);
    }

    public boolean write(String message) {
        try {
            writer.write(message);
            writer.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
