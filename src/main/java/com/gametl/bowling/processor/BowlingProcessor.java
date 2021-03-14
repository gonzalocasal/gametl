package com.gametl.bowling.processor;

import com.gametl.bowling.infrastructure.in.BowlingBuilder;
import com.gametl.bowling.infrastructure.out.BowlingExporter;
import com.gametl.bowling.model.BowlingGame;
import com.gametl.common.processor.GameProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Log4j2
public class BowlingProcessor implements GameProcessor {

    private final BowlingBuilder builder;
    private final BowlingExporter exporter;

    @Override
    public void process() throws Exception {
        log.info("Start building Bowling game...");
        BowlingGame game = builder.build();
        log.info("Bowling game build successfully.");

        log.info("Start exporting Bowling game...");
        exporter.export(game);
        log.info("Bowling export successfully.");
    }
}
