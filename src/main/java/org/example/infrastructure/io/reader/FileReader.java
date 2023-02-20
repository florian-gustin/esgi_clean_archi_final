package org.example.infrastructure.io.reader;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader implements Reader<String> {

    private final String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            return IOUtils.toString(fis, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
