package com.gametl.bowling.model;

import com.gametl.common.model.Play;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gametl.bowling.util.BowlingConstants.*;

@Getter
@Setter
@ToString
public class BowlingPlay implements Play {

    private String playerName;
    private int score;
    private boolean isFoul;

    public static BowlingPlay getInstanceFromFile(String fileLine) {
        BowlingPlay instance = new BowlingPlay();
        instance.buildFromFile(fileLine);
        return instance;
    }

    @Override
    public void buildFromFile(String fileLine) {
        String[] split = fileLine.split(BOWLING_FILE_ROW_SPLIT_REGEX);
        this.playerName = split[0].trim();
        parseScore(split[1].trim());
    }

    private void parseScore(String scoreStr) {
        try {
            this.score = Integer.parseInt(scoreStr);
            if (score < 0 || score > BOWLING_PLAY_MAX_SCORE) {
                throw new RuntimeException(String.format("Invalid score value: %s", scoreStr));
            }
            this.isFoul = false;
        } catch (NumberFormatException exception) {
            parseFoul(scoreStr);
        }
    }

    private void parseFoul(String scoreStr) {
        if (BOWLING_FOUL_CHAR.equalsIgnoreCase(scoreStr)) {
            this.score = BOWLING_PLAY_FOUL_SCORE;
            this.isFoul = true;
        } else {
            throw new RuntimeException(String.format("Invalid score value: %s", scoreStr));
        }
    }

}
