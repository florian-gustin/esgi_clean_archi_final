package org.example.infrastructure.io.reader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

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
