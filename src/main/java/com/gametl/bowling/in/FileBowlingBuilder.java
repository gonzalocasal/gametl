package com.gametl.bowling.in;

import com.gametl.bowling.model.BowlingGame;
import com.gametl.bowling.model.BowlingPlay;
import com.gametl.common.infrastructure.in.FileReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@AllArgsConstructor
@Component
public class FileBowlingBuilder {

    private final FileReader fileReader;

    public BowlingGame build(String filePath) throws Exception {
        BowlingGame bowlingGame = new BowlingGame();
        fileReader.readFile(filePath, processRow(bowlingGame));
        return bowlingGame;
    }

    private Consumer<String> processRow(BowlingGame bowlingGame) {
        return row -> bowlingGame.addPlay(BowlingPlay.getInstanceFromFile(row));
    }

}