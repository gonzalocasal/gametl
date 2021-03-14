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

@Log4j2
@Component
public class BowlingFileExporter implements BowlingExporter {

    private final String fileOutPath;
    private final GameFileWriter fileWriter;
    private final BowlingFileOutTemplate template;

    @Autowired
    public BowlingFileExporter(@Value("${file.out.path}") String fileOutPath, GameFileWriter fileWriter, BowlingFileOutTemplate template) {
        this.fileOutPath = fileOutPath;
        this.fileWriter = fileWriter;
        this.template = template;
    }

    @Override
    public void export(BowlingGame game) throws Exception {

        List<String> lines = new ArrayList<>();
        for (BowlingPlayer player : game.getPlayersMap().values()) {
            if (player.getScoreBoard().isComplete())
                lines.addAll(template.buildLines(player));
            else
                log.warn("Player {} ignored. Game is not completed.", player.getName());
        }

        fileWriter.export(fileOutPath, lines);
    }

}