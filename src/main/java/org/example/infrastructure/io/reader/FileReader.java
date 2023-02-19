package org.example.infrastructure.io.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader implements Reader<String> {

    private final File file;

    public FileReader(File file) {
        this.file = file;
    }

    @Override
    public String read() {
        try {
            String data = "";
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
