package com.gametl.bowling.integration;

import com.gametl.bowling.infrastructure.in.BowlingFileBuilder;
import com.gametl.bowling.infrastructure.out.BowlingFileExporter;
import com.gametl.bowling.model.BowlingGame;
import com.gametl.bowling.model.BowlingPlayer;
import com.gametl.bowling.model.BowlingScoreFrame;
import com.gametl.common.infrastructure.in.GameFileReader;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class BowlingFileIntegrationTest {

  @Autowired
  private BowlingFileBuilder builder;

  @Autowired
  private BowlingFileExporter exporter;

  @Autowired
  private GameFileReader fileReader;

  @Value("${file.out.path}")
  private String fileOutPath;


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

    List<String> lines = new ArrayList<>();
    fileReader.readFile(fileOutPath, readOutPutRow(lines));

    Assertions.assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(0));
    Assertions.assertEquals("Bob", lines.get(1));
    Assertions.assertEquals("Pin falls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t\t", lines.get(2));
    Assertions.assertEquals("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t", lines.get(3));

    Assertions.assertEquals("", lines.get(4));

    Assertions.assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(5));
    Assertions.assertEquals("Jeff", lines.get(6));
    Assertions.assertEquals("Pin falls\t \tX\t7\t/\t9\t0\t \tX\t0\t8\t8\t/\tF\t6\t \tX\t \tX\tX\t8\t1\t", lines.get(7));
    Assertions.assertEquals("Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\t\t", lines.get(8));

    Assertions.assertEquals("", lines.get(9));

    Assertions.assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(10));
    Assertions.assertEquals("John", lines.get(11));
    Assertions.assertEquals("Pin falls\t3\t/\t6\t3\t \tX\t8\t1\t \tX\t \tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\t", lines.get(12));
    Assertions.assertEquals("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\t\t", lines.get(13));

    Assertions.assertEquals("", lines.get(14));

    Assertions.assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10", lines.get(15));
    Assertions.assertEquals("Alice", lines.get(16));
    Assertions.assertEquals("Pin falls\t \tX\t \tX\t \tX\t \tX\t \tX\t \tX\t \tX\t \tX\t \tX\tX\tX\tX\t", lines.get(17));
    Assertions.assertEquals("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\t\t", lines.get(18));


  }

  private int getTotalScore(BowlingPlayer player) {
    return Arrays.stream(player.getScoreBoard().getScoreFrames()).mapToInt(BowlingScoreFrame::getFrameScore).sum();
  }

  private Consumer<String> readOutPutRow(List<String> lines) {
    return lines::add;
  }

}