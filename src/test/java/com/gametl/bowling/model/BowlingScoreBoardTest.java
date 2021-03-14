package com.gametl.bowling.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gametl.bowling.util.BowlingConstants.*;

public class BowlingScoreBoardTest {

    @Test
    void scoreBoardTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "7");
        BowlingPlay secondTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "2");
        BowlingPlay third = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "5");

        BowlingScoreBoard scoreBoard = new BowlingScoreBoard();
        scoreBoard.addPlay(firstTry);
        scoreBoard.addPlay(secondTry);
        scoreBoard.addPlay(third);

        Assertions.assertEquals(9, scoreBoard.getScoreFrames()[0].getFrameScore());
        Assertions.assertEquals("Bob", scoreBoard.getScoreFrames()[0].getFirstTry().getPlayerName());
    }

    @Test
    void scoreBoardSpareTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "7");
        BowlingPlay secondTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "3");
        BowlingPlay third = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "5");

        BowlingScoreBoard scoreBoard = new BowlingScoreBoard();
        scoreBoard.addPlay(firstTry);
        scoreBoard.addPlay(secondTry);
        scoreBoard.addPlay(third);

        Assertions.assertEquals(15, scoreBoard.getScoreFrames()[0].getFrameScore());
    }

    @Test
    void scoreBoardStrikeTest() {
        BowlingPlay firstTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "10");
        BowlingPlay secondTry = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "3");
        BowlingPlay third = BowlingPlay.getInstanceFromFile("Bob" + BOWLING_FILE_ROW_SPLIT_REGEX + "5");

        BowlingScoreBoard scoreBoard = new BowlingScoreBoard();
        scoreBoard.addPlay(firstTry);
        scoreBoard.addPlay(secondTry);
        scoreBoard.addPlay(third);

        Assertions.assertEquals(18, scoreBoard.getScoreFrames()[0].getFrameScore());
    }

}
