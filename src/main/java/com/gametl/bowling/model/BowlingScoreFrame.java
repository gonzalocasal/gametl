package com.gametl.bowling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gametl.bowling.util.BowlingConstants.BOWLING_FRAMES_INDEX_MAX;
import static com.gametl.bowling.util.BowlingConstants.BOWLING_PLAY_MAX_SCORE;

/**
 * Represents 1 of the 10 Bowling Score Frame with the 2 score boxes for the first 9 turns and 3 boxes for the last turn.
 */
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
    private boolean isCompleted;

    public BowlingScoreFrame(int index) {
        this.isLastFrame = (index == BOWLING_FRAMES_INDEX_MAX);
    }

    /**
     * @implNote Add the points from next frames if this frame has a result with bonus play, Strike or Spare.
     */
    public void addBonusPoints(int bonusPoints) {
        this.frameScore += bonusPoints;
        bonusCount ++;
    }

    /**
     * @implNote Register a new player throw.
     */
    public void addTry(BowlingPlay play) {
        if (firstTry == null) {
            countFirstBall(play);
        } else if (secondTry == null) {
            countSecondBall(play);
        } else if (isLastFrame && (isBonusFrame())) {
            countThirdBall(play);
        }
    }

    /**
     * @implNote Register the player first throw of the turn.
     */
    private void countFirstBall(BowlingPlay play) {
        firstTry = play;
        frameScore = play.getScore();
        if (play.isStrike()) {
            isStrike = true;
            if (!isLastFrame) {
                isCompleted = true;
            }
        }
    }

    /**
     * @implNote Register the player second throw of the turn.
     */
    private void countSecondBall(BowlingPlay play) {
        secondTry = play;
        frameScore += play.getScore();
        if (frameScore == BOWLING_PLAY_MAX_SCORE) {
            isSpare = true;
        }
        if (!isLastFrame) {
            isCompleted = true;
        } else if (!isSpare && !isStrike) {
            isCompleted = true;
        }
    }

    /**
     * @implNote Register the player third throw of the turn. Only if is the last turn and the first throw is a Strike or the second throw completes an Spare.
     */
    private void countThirdBall(BowlingPlay play) {
        thirdTry = play;
        frameScore += play.getScore();
        isCompleted = true;
    }

    /**
     *
     * If this is not the last frame is completed if the first throw is a Strike or the second throw is registered.
     * If this is the last frame, same as previous frames but is a bonus frame the third must be Registered.
     *
     * @return if the current frame is completed.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     *
     * @return true if the first throw registered a Strike or the second throw completed a Spare
     */
    private boolean isBonusFrame() {
        return isStrike || isSpare;
    }

}
