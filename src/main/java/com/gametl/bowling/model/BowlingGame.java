package com.gametl.bowling.model;

import com.gametl.common.model.Game;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@ToString
public class BowlingGame implements Game<BowlingPlay> {

    private final Map<String, BowlingPlayer> playersMap = new HashMap<>();

    @Override
    public void addPlay(BowlingPlay play) {
        String playerName = play.getPlayerName();
        BowlingPlayer player = getPlayer(playerName);
        player.addPlay(play);
    }

    public void getResults() {
        playersMap.values().forEach(BowlingPlayer::getTotalScore);
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
