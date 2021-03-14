package com.gametl.bowling.infrastructure.in;

import com.gametl.bowling.model.BowlingGame;

/**
 * For any Bowling Game File, DB, Cloud etc importer.
 */
public interface BowlingBuilder {

    BowlingGame build() throws Exception;

}
