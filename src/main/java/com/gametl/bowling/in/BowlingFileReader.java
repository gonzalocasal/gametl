package com.gametl.bowling.in;

import com.gametl.bowling.model.BowlingPlay;
import com.gametl.common.infrastructure.in.FileReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Component
public class BowlingFileReader {

    private final FileReader fileReader;

    public List<BowlingPlay> readPlays(String filePath) {
        List<BowlingPlay> plays = new ArrayList<>();
        fileReader.readFile(filePath, processRow(plays));
        return plays;
    }

    private Consumer<String> processRow(List<BowlingPlay> plays) {
        return row -> {
            String[] split = row.split("\t");
            plays.add(new BowlingPlay(split[0], split[1]));
        };
    }

}
