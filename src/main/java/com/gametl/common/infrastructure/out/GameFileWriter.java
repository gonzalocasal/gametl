package com.gametl.common.infrastructure.out;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class GameFileWriter<T> {

    private final Writeable<T> template;

    public void export (String outPath, List<T> elements) throws IOException {
        log.info("Writing the file {}", outPath);
        int linesCount = 0;

        Path path = Paths.get(outPath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (T element : elements) {
                List <String> lines = template.getObjectLines(element);
                for (String l : lines) {
                    writer.write(l);
                    linesCount++;
                }
            }
        }
        log.info("File write complete. {} lines written.", linesCount);
    }

}