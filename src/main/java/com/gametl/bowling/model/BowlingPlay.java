package com.gametl.bowling.model;

import com.gametl.common.model.Play;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gametl.bowling.util.BowlingConstants.*;


/**
 * Represents a throw of o Bowling Game Player.
 */
@Getter
@Setter
@ToString
public class BowlingPlay implements Play {

    private String playerName;
    private int score;
    private boolean isFoul;
    private boolean isStrike;

    public static BowlingPlay getInstanceFromFile(String fileLine) {
        BowlingPlay instance = new BowlingPlay();
        instance.buildFromFile(fileLine);
        return instance;
    }

    @Override
    public void buildFromFile(String fileLine) {
        String[] split = fileLine.split(BOWLING_FILE_ROW_SPLIT_REGEX);

        this.playerName = split[BOWLING_FILE_ROW_NAME_INDEX].trim();
        this.isFoul = checkFoul(split[BOWLING_FILE_ROW_SCORE_INDEX].trim());
        this.score = parseScore(split[BOWLING_FILE_ROW_SCORE_INDEX].trim());
        this.isStrike = this.score == BOWLING_PLAY_MAX_SCORE;
    }

    /**
     * @return the input Score value. Validate that the input score is a number between 0 and 10. If is a Foul throw, the score is 0.
     */
    private int parseScore(String scoreStr) {
        int parsedScore = BOWLING_PLAY_FOUL_SCORE;
        if (!isFoul){
            try {
                parsedScore = Integer.parseInt(scoreStr);
                if (isOutOfRange(parsedScore)) {
                    invalidScore(scoreStr);
                }
            } catch (NumberFormatException e) {
                invalidScore(scoreStr);
            }
        }
        return parsedScore;
    }

    /**
     * @implNote check the score range.
     */
    private boolean isOutOfRange(int parsedScore) {
        return parsedScore < 0 || parsedScore > BOWLING_PLAY_MAX_SCORE;
    }

    /**
     * @implNote check if the user throw is a Foul.
     */
    private boolean checkFoul(String scoreStr) {
        return BOWLING_FOUL_CHAR.equalsIgnoreCase(scoreStr);
    }

    private void invalidScore(String scoreStr) {
        throw new RuntimeException(String.format("Invalid score value: %s", scoreStr));
    }

}
