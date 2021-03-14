package com.gametl.bowling.processor;

import com.gametl.bowling.infrastructure.in.BowlingBuilder;
import com.gametl.bowling.model.BowlingGame;
import com.gametl.common.processor.GameProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BowlingProcessor implements GameProcessor {

    private final BowlingBuilder builder;

    @Override
    public void process() throws Exception {
        BowlingGame build = builder.build();
        build.getResults();
    }
}
