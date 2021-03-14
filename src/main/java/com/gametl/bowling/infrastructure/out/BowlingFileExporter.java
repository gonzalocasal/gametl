package com.gametl.bowling.infrastructure.out;

import com.gametl.bowling.model.BowlingGame;
import com.gametl.bowling.model.BowlingPlayer;
import com.gametl.common.infrastructure.out.GameFileWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Writes a Bowling Game result from a flat File.
 */
@Log4j2
@Component
public class BowlingFileExporter implements BowlingExporter {

    private final String fileOutPath;
    private final GameFileWriter<BowlingPlayer> fileWriter;

    @Autowired
    public BowlingFileExporter(@Value("${file.out.path}") String fileOutPath, GameFileWriter<BowlingPlayer> fileWriter) {
        this.fileOutPath = fileOutPath;
        this.fileWriter = fileWriter;
    }

    @Override
    public void export(BowlingGame game) throws Exception {
        List<BowlingPlayer> players = new ArrayList<>(game.getPlayersMap().values());
        fileWriter.export(fileOutPath, players);
    }

}