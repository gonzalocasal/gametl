package com.gametl.bowling.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gametl.bowling.util.BowlingConstants.BOWLING_FILE_ROW_SPLIT_REGEX;
import static com.gametl.bowling.util.BowlingConstants.BOWLING_PLAY_FOUL_SCORE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BowlingPlayTest {

    @Test
    void validFileLineTest() {
        BowlingPlay play = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "7");
        Assertions.assertEquals(7, play.getScore());
        Assertions.assertEquals("Bob", play.getPlayerName());
    }

    @Test
    void foulFileLineTest() {
        BowlingPlay play = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "F");
        Assertions.assertTrue(play.isFoul());
        Assertions.assertEquals(BOWLING_PLAY_FOUL_SCORE, play.getScore());
    }

    @Test
    void invalidFileLineTest() {
        assertThrows(RuntimeException.class, () -> BowlingPlay.getInstanceFromFile("Alice" + BOWLING_FILE_ROW_SPLIT_REGEX + "-1"));
        assertThrows(RuntimeException.class, () -> BowlingPlay.getInstanceFromFile("Alice" + BOWLING_FILE_ROW_SPLIT_REGEX + "11"));
        assertThrows(RuntimeException.class, () -> BowlingPlay.getInstanceFromFile("Alice" + BOWLING_FILE_ROW_SPLIT_REGEX + "A"));
    }

}
