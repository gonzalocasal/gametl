package com.gametl.bowling.integration;

import com.gametl.bowling.infrastructure.in.BowlingFileBuilder;
import com.gametl.bowling.infrastructure.out.BowlingFileExporter;
import com.gametl.bowling.model.BowlingGame;
import com.gametl.bowling.model.BowlingPlayer;
import com.gametl.bowling.model.BowlingScoreFrame;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")

public class BowlingFileIntegrationTest {

  @Autowired
  private BowlingFileBuilder builder;

  @Autowired
  private BowlingFileExporter exporter;


  @Test
  public void bowlingGameBuilderTest() throws Exception {

    BowlingGame game = builder.build();
    exporter.export(game);

    BowlingPlayer alice = game.getPlayersMap().get("Alice");
    Assertions.assertEquals(300, getTotalScore(alice));

    BowlingPlayer bob = game.getPlayersMap().get("Bob");
    Assertions.assertEquals(0, getTotalScore(bob));

    BowlingPlayer john = game.getPlayersMap().get("John");
    Assertions.assertEquals(151, getTotalScore(john));

    BowlingPlayer jeff = game.getPlayersMap().get("Jeff");
    Assertions.assertEquals(167, getTotalScore(jeff));

  }

  private int getTotalScore(BowlingPlayer player) {
    return Arrays.stream(player.getScoreBoard().getScoreFrames()).mapToInt(BowlingScoreFrame::getFrameScore).sum();
  }

}