package com.gametl.bowling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@Setter
@ToString
public class BowlingPlayer {

    private String name;
    private BowlingScoreBoard scoreBoard;

    public BowlingPlayer(String name) {
        this.name = name;
        this.scoreBoard = new BowlingScoreBoard();
    }

    public void addPlay(BowlingPlay play) {
        if (!scoreBoard.isComplete())
            this.scoreBoard.addPlay(play);
    }

    public void getTotalScore () {
        int result = Arrays.stream(scoreBoard.getScoreFrames()).mapToInt(BowlingScoreFrame::getFrameScore).sum();
        System.out.println(result);
    }
}
