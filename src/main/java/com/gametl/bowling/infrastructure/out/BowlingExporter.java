package com.gametl.bowling.infrastructure.out;

import com.gametl.bowling.model.BowlingGame;

/**
 * For any Bowling Game File, DB, Cloud etc exporters.
 */
public interface BowlingExporter {

    void export(BowlingGame game);

}
