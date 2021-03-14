package com.gametl.bowling.infrastructure.out;

import com.gametl.bowling.model.BowlingGame;

public interface BowlingExporter {

    void export(BowlingGame game) throws Exception;

}
