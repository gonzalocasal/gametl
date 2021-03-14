package com.gametl.common.infrastructure.in;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * Generic class to read Games data from a flat File.
 */
@Component
@Log4j2
public class GameFileReader {

    public void readFile(String filePath, Consumer<String> consumer) throws Exception {
        log.info("Reading from file {}", filePath);
        int linesCount = 0;
        try (FileInputStream fileStream = new FileInputStream(filePath)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
            String line;
            while ((line = br.readLine()) != null)   {
                consumer.accept(line);
                linesCount++;
            }
        }
        log.info("File read complete. {} records read.", linesCount);
    }

}