package com.gametl.bowling.integration;

import com.gametl.bowling.infrastructure.in.BowlingFileBuilder;
import com.gametl.bowling.infrastructure.out.BowlingFileExporter;
import com.gametl.bowling.model.BowlingGame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


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
  }

}