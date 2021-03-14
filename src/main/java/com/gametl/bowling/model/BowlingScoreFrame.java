package com.gametl.bowling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gametl.bowling.util.BowlingConstants.BOWLING_FRAMES_INDEX_MAX;
import static com.gametl.bowling.util.BowlingConstants.BOWLING_PLAY_MAX_SCORE;

@Getter
@Setter
@ToString
public class BowlingScoreFrame {

    private int frameScore;
    private int bonusCount;

    private BowlingPlay firstTry;
    private BowlingPlay secondTry;
    private BowlingPlay thirdTry;

    private boolean isStrike;
    private boolean isSpare;
    private boolean isLastFrame;

    public BowlingScoreFrame(int index) {
        this.isLastFrame = (index == BOWLING_FRAMES_INDEX_MAX);
    }

    public void addBonusPoints(int bonusPoints) {
        this.frameScore += bonusPoints;
        bonusCount ++;
    }

    public void addTry(BowlingPlay play) {
        if (firstTry == null) {
            countFirstBall(play);
        } else if (secondTry == null) {
            countSecondBall(play);
        } else if (isLastFrame && isStrike) {
            countThirdBall(play);
        }
    }

    private void countFirstBall(BowlingPlay play) {
        firstTry = play;
        frameScore = play.getScore();
        if (play.isStrike()) {
            isStrike = true;
        }
    }

    private void countSecondBall(BowlingPlay play) {
        secondTry = play;
        frameScore += play.getScore();
        if (frameScore == BOWLING_PLAY_MAX_SCORE) {
            isSpare = true;
        }
    }

    private void countThirdBall(BowlingPlay play) {
        thirdTry = play;
        frameScore += play.getScore();
    }

    public boolean isCompleted() {
        if (isLastFrame) {
            return (isStrike && thirdTry != null) || (!isStrike && secondTry != null);
        } else {
            return isStrike || secondTry != null;
        }
    }

}
