package com.gametl.bowling.infrastructure.out;

import com.gametl.bowling.model.BowlingPlay;
import com.gametl.bowling.model.BowlingPlayer;
import com.gametl.bowling.model.BowlingScoreFrame;
import com.gametl.common.infrastructure.out.Writeable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.gametl.bowling.util.BowlingConstants.*;

@NoArgsConstructor
@Component
@Getter
public class BowlingFileOutTemplate implements Writeable<BowlingPlayer> {

    @Value("${filet.out.template.header}")
    private String header;

    @Value("${filet.out.template.pin.falls}")
    private String pinFalls;

    @Value("${filet.out.template.score}")
    private String score;

    @Override
    public List<String> getObjectLines(BowlingPlayer object) {
        List<String> playerLines = new ArrayList<>();
        playerLines.add(header);
        playerLines.add(BOWLING_FILE_ROW_END_REGEX);
        playerLines.add(object.getName());
        playerLines.add(BOWLING_FILE_ROW_END_REGEX);
        playerLines.add(buildPinFalls(object));
        playerLines.add(BOWLING_FILE_ROW_END_REGEX);
        playerLines.add(buildScores(object));
        playerLines.add(BOWLING_FILE_ROW_END_REGEX);
        playerLines.add(BOWLING_FILE_ROW_END_REGEX);
        return playerLines;
    }

    private String buildPinFalls(BowlingPlayer player) {
        StringBuilder pinFallsBuilder = new StringBuilder();
        pinFallsBuilder.append(pinFalls);
        for(BowlingScoreFrame frame : player.getScoreBoard().getScoreFrames()) {
            pinFallsBuilder.append(buildFrame(frame));
        }
        return pinFallsBuilder.toString();
    }

    private String buildFrame(BowlingScoreFrame frame) {
        StringBuilder frameBuilder = new StringBuilder();

        if (!frame.isLastFrame()) {
            firstBox(frame, frameBuilder);
            frameBuilder.append(BOWLING_FILE_ROW_SPLIT_REGEX);
            secondBox(frame, frameBuilder);
        } else {
            lastFrameBox(frameBuilder, frame.getFirstTry());
            frameBuilder.append(BOWLING_FILE_ROW_SPLIT_REGEX);
            lastFrameBox(frameBuilder, frame.getSecondTry());
            frameBuilder.append(BOWLING_FILE_ROW_SPLIT_REGEX);
            lastFrameBox(frameBuilder, frame.getThirdTry());
        }
        frameBuilder.append(BOWLING_FILE_ROW_SPLIT_REGEX);

        return frameBuilder.toString();
    }

    private void firstBox(BowlingScoreFrame frame, StringBuilder frameBuilder) {
        if (frame.isStrike()) {
            frameBuilder.append(BOWLING_STRIKE_FIRST_ROW_CHAR);
        } else {
            if (frame.getFirstTry().isFoul())
                frameBuilder.append(BOWLING_FOUL_CHAR);
            else
                frameBuilder.append(frame.getFirstTry().getScore());
        }
    }

    private void secondBox(BowlingScoreFrame frame, StringBuilder frameBuilder) {
        if (frame.isStrike()) {
            frameBuilder.append(BOWLING_STRIKE_CHAR);
        } else {
            if (frame.getSecondTry().isFoul())
                frameBuilder.append(BOWLING_FOUL_CHAR);
            else
            if (frame.isSpare())
                frameBuilder.append(BOWLING_SPARE_CHAR);
            else
                frameBuilder.append(frame.getSecondTry().getScore());
        }
    }

    private void lastFrameBox(StringBuilder frameBuilder, BowlingPlay play) {
        if (play != null) {
            if (play.isStrike())
                frameBuilder.append(BOWLING_STRIKE_CHAR);
            if (play.isFoul())
                frameBuilder.append(BOWLING_FOUL_CHAR);
            if (!play.isFoul() && !play.isStrike())
                frameBuilder.append(play.getScore());
        }
    }

    private String buildScores(BowlingPlayer player) {
        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append(score);

        int totalScore = 0;
        for(BowlingScoreFrame frame : player.getScoreBoard().getScoreFrames()) {
            totalScore += frame.getFrameScore();
            scoreBuilder.append(totalScore);
            scoreBuilder.append(BOWLING_FILE_ROW_SPLIT_REGEX);
            scoreBuilder.append(BOWLING_FILE_ROW_SPLIT_REGEX);
        }
        return scoreBuilder.toString();
    }

}
