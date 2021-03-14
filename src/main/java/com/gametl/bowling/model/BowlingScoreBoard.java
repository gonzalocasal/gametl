package com.gametl.bowling.model;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;

import static com.gametl.bowling.util.BowlingConstants.*;


@Getter
@ToString
public class BowlingScoreBoard {

    private final BowlingScoreFrame[] scoreFrames;

    private final LinkedList<BowlingScoreFrame> strikeBonusFrames = new LinkedList<>();

    private BowlingScoreFrame spareBonusFrame;

    private int frameNumber;

    public BowlingScoreBoard() {
        this.scoreFrames = new BowlingScoreFrame[BOWLING_FRAMES_COUNT_MAX];
    }

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

    private void bonusPlayValidation(BowlingScoreFrame currentFrame) {
        if (currentFrame.isSpare() && frameNumber < BOWLING_FRAMES_INDEX_MAX) {
            this.spareBonusFrame = currentFrame;
        }
        if (currentFrame.isStrike() && frameNumber < BOWLING_FRAMES_INDEX_MAX) {
            this.strikeBonusFrames.add(currentFrame);
        }
    }

    private BowlingScoreFrame getCurrentFrame() {
        BowlingScoreFrame currentFrame = this.scoreFrames[frameNumber];
        if (currentFrame == null) {
            currentFrame = new BowlingScoreFrame(frameNumber);
            this.scoreFrames[frameNumber] = currentFrame;
        }
        return currentFrame;
    }

    private void addToSpareBonusFrame(int score) {
        if (spareBonusFrame != null) {
            spareBonusFrame.addBonusPoints(score);
            spareBonusFrame = null;
        }
    }

    private void addToStrikeBonusFrames(int score) {
        if (!strikeBonusFrames.isEmpty()) {
            strikeBonusFrames.forEach(f -> f.addBonusPoints(score));
            strikeBonusFrames.removeIf(f -> f.getBonusCount() == BOWLING_BONUS_MAX_COUNT);
        }
    }

    public boolean isComplete() {
        BowlingScoreFrame lastFrame = scoreFrames[BOWLING_FRAMES_INDEX_MAX];
        return lastFrame != null && lastFrame.isCompleted();
    }

}
