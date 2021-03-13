package com.gametl.bowling.processor;

import com.gametl.bowling.in.BowlingFileReader;
import com.gametl.bowling.model.BowlingPlay;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BowlingProcessor {

    private final BowlingFileReader bowlingFileReader;

    public void process(String filePath) {
        List<BowlingPlay> plays = bowlingFileReader.readPlays(filePath);
        plays.forEach(p -> System.out.println(p.toString()));

    }

}
