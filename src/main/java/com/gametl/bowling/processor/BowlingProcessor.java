package com.gametl.bowling.processor;

import com.gametl.bowling.infrastructure.in.BowlingBuilder;
import com.gametl.bowling.infrastructure.out.BowlingExporter;
import com.gametl.bowling.model.BowlingGame;
import com.gametl.common.processor.GameProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BowlingProcessor implements GameProcessor {

    private final BowlingBuilder builder;
    private final BowlingExporter exporter;

    @Override
    public void process() throws Exception {
        BowlingGame game = builder.build();
        exporter.export(game);
    }
}
