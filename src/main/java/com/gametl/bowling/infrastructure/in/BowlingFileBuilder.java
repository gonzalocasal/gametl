package com.gametl.bowling.infrastructure.in;

import com.gametl.bowling.model.BowlingGame;
import com.gametl.bowling.model.BowlingPlay;
import com.gametl.common.infrastructure.in.GameFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class BowlingFileBuilder implements BowlingBuilder {

    private final String filePath;
    private final GameFileReader fileReader;

    @Autowired
    public BowlingFileBuilder(@Value("${file.in.path}") String filePath, GameFileReader fileReader) {
        this.filePath = filePath;
        this.fileReader = fileReader;
    }

    public BowlingGame build() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();
        fileReader.readFile(filePath, processRow(bowlingGame));
        return bowlingGame;
    }

    private Consumer<String> processRow(BowlingGame bowlingGame) {
        return row -> bowlingGame.addPlay(BowlingPlay.getInstanceFromFile(row));
    }

}