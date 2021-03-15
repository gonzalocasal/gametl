package com.gametl.bowling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Represents one of the Bowling Game Players.
 */
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

    /**
     * @implNote Register a new throw of the player in the score board.
     */
    public void addPlay(BowlingPlay play) {
        if (!scoreBoard.isComplete())
            this.scoreBoard.addPlay(play);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPlayer player = (BowlingPlayer) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
