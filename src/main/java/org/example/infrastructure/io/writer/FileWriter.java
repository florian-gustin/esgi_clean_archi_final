package org.example.infrastructure.io.writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileWriter implements Writer {

    private final Path filePath;
    private File yourFile;
    public FileWriter(String fileName) throws IOException {
        yourFile = new File(fileName);
        if(!yourFile.exists())
            if(yourFile.getParentFile() != null)
                yourFile.getParentFile().mkdirs();
            yourFile.createNewFile();
        this.filePath = Path.of(fileName);
    }

    public boolean write(String message) {
        try {
            if(!yourFile.exists())
                yourFile.createNewFile();

            Files.writeString(this.filePath, message, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
