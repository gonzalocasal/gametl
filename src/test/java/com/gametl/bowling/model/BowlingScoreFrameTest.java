package com.gametl.bowling.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gametl.bowling.util.BowlingConstants.*;

public class BowlingScoreFrameTest {

    @Test
    void scoreFrameSpareTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "7");
        BowlingPlay secondTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "3");

        BowlingScoreFrame scoreFrame = new BowlingScoreFrame(0);
        scoreFrame.addTry(firstTry);
        scoreFrame.addTry(secondTry);

        Assertions.assertEquals(10, scoreFrame.getFrameScore());
        Assertions.assertTrue(scoreFrame.isCompleted());
        Assertions.assertTrue(scoreFrame.isSpare());
        Assertions.assertFalse(scoreFrame.isStrike());
    }

    @Test
    void scoreFrameStrikeTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + BOWLING_PLAY_MAX_SCORE);

        BowlingScoreFrame scoreFrame = new BowlingScoreFrame(0);
        scoreFrame.addTry(firstTry);

        Assertions.assertEquals(BOWLING_PLAY_MAX_SCORE, scoreFrame.getFrameScore());
        Assertions.assertTrue(scoreFrame.isCompleted());
        Assertions.assertFalse(scoreFrame.isSpare());
        Assertions.assertTrue(scoreFrame.isStrike());
    }

    @Test
    void scoreFrameLastFrameTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "5");
        BowlingPlay secondTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "3");
        BowlingPlay thirdTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "1");

        BowlingScoreFrame scoreFrame = new BowlingScoreFrame(BOWLING_FRAMES_INDEX_MAX);
        scoreFrame.addTry(firstTry);
        scoreFrame.addTry(secondTry);
        scoreFrame.addTry(thirdTry);

        Assertions.assertFalse(scoreFrame.isStrike());
        Assertions.assertTrue(scoreFrame.isLastFrame());
        Assertions.assertTrue(scoreFrame.isCompleted());
        Assertions.assertEquals(8, scoreFrame.getFrameScore());
        Assertions.assertNull(scoreFrame.getThirdTry());


        firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "10");
        scoreFrame = new BowlingScoreFrame(BOWLING_FRAMES_INDEX_MAX);
        scoreFrame.addTry(firstTry);
        scoreFrame.addTry(secondTry);
        scoreFrame.addTry(thirdTry);

        Assertions.assertTrue(scoreFrame.isStrike());
        Assertions.assertTrue(scoreFrame.isLastFrame());
        Assertions.assertTrue(scoreFrame.isCompleted());
        Assertions.assertEquals(14, scoreFrame.getFrameScore());
        Assertions.assertNotNull(scoreFrame.getThirdTry());
    }

    @Test
    void scoreFrameLastFrameStrikeTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "10");
        BowlingPlay secondTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "3");
        BowlingPlay thirdTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "1");

        BowlingScoreFrame scoreFrame = new BowlingScoreFrame(BOWLING_FRAMES_INDEX_MAX);
        scoreFrame.addTry(firstTry);
        scoreFrame.addTry(secondTry);
        scoreFrame.addTry(thirdTry);

        Assertions.assertTrue(scoreFrame.isStrike());
        Assertions.assertTrue(scoreFrame.isLastFrame());
        Assertions.assertTrue(scoreFrame.isCompleted());
        Assertions.assertEquals(14, scoreFrame.getFrameScore());
        Assertions.assertNotNull(scoreFrame.getThirdTry());
    }

}
