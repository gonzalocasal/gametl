package com.gametl.common.infrastructure.in;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

@Component
public class FileReader {

    public void readFile(String filePath, Consumer<String> consumer) throws IOException {
        try (FileInputStream fileStream = new FileInputStream(filePath)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
            String line;
            while ((line = br.readLine()) != null)   {
                consumer.accept(line);
            }
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

}
