package com.gametl.common.infrastructure.out;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
@Component
public class GameFileWriter {

    public void export (String outPath, List<String> lines) throws IOException {
        log.info("Writing the file {}", outPath);
        int linesCount = 0;

        Path path = Paths.get(outPath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
                linesCount++;
            }
        }
        log.info("File write complete. {} lines written.", linesCount);
    }

}
