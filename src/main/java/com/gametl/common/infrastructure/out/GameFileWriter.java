package com.gametl.common.infrastructure.out;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class GameFileWriter {

    public void export (String outPath, List<String> lines) throws IOException {

        Path path = Paths.get(outPath);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
            }
        }
    }

}
