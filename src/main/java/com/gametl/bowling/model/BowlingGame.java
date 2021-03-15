package com.gametl.bowling.model;

import com.gametl.common.model.Game;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.*;

/**
 * Represents a Bowling Game with a Map of Players.
 */
@Getter
@Log4j2
@ToString
public class BowlingGame implements Game<BowlingPlay> {

    private final Map<String, BowlingPlayer> playersMap = new HashMap<>();
    Set<BowlingPlayer> completePlayers = new HashSet<>();

    @Override
    public void addPlay(BowlingPlay play) {
        String playerName = play.getPlayerName();
        BowlingPlayer player = getPlayer(playerName);
        player.addPlay(play);
        if (player.getScoreBoard().isComplete()) {
            completePlayers.add(player);
        }
    }

    /**
     * @return A list of players with complete score board.
     */
    public List<BowlingPlayer> getResults() {
        for(BowlingPlayer player : playersMap.values()) {
            if (!completePlayers.contains(player)) {
                log.warn("Player {} skipped from results. Scoreboard incomplete", player.getName());
            }
        }
        return new ArrayList<>(completePlayers);
    }

    private BowlingPlayer getPlayer(String playerName) {
        BowlingPlayer player = playersMap.get(playerName);
        if (player == null) {
            player = new BowlingPlayer(playerName);
            playersMap.put(playerName, player);
        }
        return player;
    }

}
