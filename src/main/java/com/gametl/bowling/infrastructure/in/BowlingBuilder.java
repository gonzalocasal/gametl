package com.gametl.bowling.infrastructure.in;

import com.gametl.bowling.model.BowlingGame;

public interface BowlingBuilder {

    BowlingGame build() throws Exception;

}
