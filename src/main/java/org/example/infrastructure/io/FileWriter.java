package org.example.infrastructure.io;

import org.example.kernel.Writer;

import java.io.File;

public class FileWriter implements Writer {

    private final File file;

    FileWriter(File file) {
        this.file = file;
    }


    public void write(String message) {

    }
}
