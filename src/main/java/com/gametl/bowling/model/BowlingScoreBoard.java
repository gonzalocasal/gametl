package com.gametl.bowling.model;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

import static com.gametl.bowling.util.BowlingConstants.*;

/**
 * Represents a Player Bowling Score board with 10 frames.
 */
@Getter
@ToString
public class BowlingScoreBoard {

    private final BowlingScoreFrame[] scoreFrames;
    private final List<BowlingScoreFrame> strikeBonusFrames;
    private BowlingScoreFrame spareBonusFrame;
    private int frameNumber;

    public BowlingScoreBoard() {
        this.scoreFrames = new BowlingScoreFrame[BOWLING_FRAMES_COUNT_MAX];
        this.strikeBonusFrames = new LinkedList<>();
    }

    /**
     * @implNote Register a new Player throw on the current frame.
     */
    public void addPlay(BowlingPlay play){

        addToStrikeBonusFrames(play.getScore());
        addToSpareBonusFrame(play.getScore());

        BowlingScoreFrame currentFrame = getCurrentFrame();
        currentFrame.addTry(play);
        bonusPlayValidation(currentFrame);

        if (currentFrame.isCompleted() && frameNumber < BOWLING_FRAMES_INDEX_MAX) {
            this.frameNumber++;
        }
    }

    /**
     * @implNote After the registration of a new throw, this validate if the current frame is a bonus frame: Spare or Strike.
     */
    private void bonusPlayValidation(BowlingScoreFrame currentFrame) {
        if (currentFrame.isSpare()) {
            this.spareBonusFrame = currentFrame;
        }
        if (currentFrame.isStrike() && frameNumber < BOWLING_FRAMES_INDEX_MAX) {
            this.strikeBonusFrames.add(currentFrame);
        }
    }

    /**
     * If the current frame has registered the two score boxes, generates a new frame and increment the current frame index.
     *
     *  @return the current frame to register a new throw.
     *
     */
    private BowlingScoreFrame getCurrentFrame() {
        BowlingScoreFrame currentFrame = this.scoreFrames[frameNumber];
        if (currentFrame == null) {
            currentFrame = new BowlingScoreFrame(frameNumber);
            this.scoreFrames[frameNumber] = currentFrame;
        }
        return currentFrame;
    }

    /**
     * @implNote Sum the recent throw score to previous frame with Spare bonus
     */
    private void addToSpareBonusFrame(int score) {
        if (spareBonusFrame != null) {
            spareBonusFrame.addBonusPoints(score);
            spareBonusFrame = null;
        }
    }

    /**
     * @implNote Sum the recent throw score to previous frames with Strike bonus
     */
    private void addToStrikeBonusFrames(int score) {
        if (!strikeBonusFrames.isEmpty()) {
            strikeBonusFrames.forEach(f -> f.addBonusPoints(score));
            strikeBonusFrames.removeIf(f -> f.getBonusCount() == BOWLING_BONUS_MAX_COUNT);
        }
    }

    /**
     * @return true if the last frame is created and completed.
     */
    public boolean isComplete() {
        BowlingScoreFrame lastFrame = scoreFrames[BOWLING_FRAMES_INDEX_MAX];
        return lastFrame != null && lastFrame.isCompleted();
    }

}
