package com.gametl.bowling.integration;

import com.gametl.bowling.infrastructure.in.BowlingFileBuilder;
import com.gametl.bowling.infrastructure.out.BowlingFileExporter;
import com.gametl.bowling.model.BowlingGame;
import com.gametl.bowling.model.BowlingPlayer;
import com.gametl.bowling.model.BowlingScoreFrame;
import com.gametl.common.infrastructure.out.Writeable;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class BowlingFileIntegrationTest {

  @Autowired
  private BowlingFileBuilder builder;

  @Autowired
  private BowlingFileExporter exporter;

  @Autowired
  private Writeable<BowlingPlayer> template;

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

  @Test
  public void bowlingTemplateTest() throws Exception {

    BowlingGame game = builder.build();
    exporter.export(game);

    BowlingPlayer john = game.getPlayersMap().get("John");
    List<String> lines = template.getObjectLines(john);

    Assertions.assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
    Assertions.assertEquals("\n", lines.get(1));
    Assertions.assertEquals("John", lines.get(2));
    Assertions.assertEquals("\n", lines.get(3));
    Assertions.assertEquals("Pin falls\t3\t/\t6\t3\t \tX\t8\t1\t \tX\t \tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\t", lines.get(4));
    Assertions.assertEquals("\n", lines.get(5));
    Assertions.assertEquals("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\t\t", lines.get(6));
    Assertions.assertEquals("\n", lines.get(7));
    Assertions.assertEquals("\n", lines.get(8));
  }

  private int getTotalScore(BowlingPlayer player) {
    return Arrays.stream(player.getScoreBoard().getScoreFrames()).mapToInt(BowlingScoreFrame::getFrameScore).sum();
  }

}