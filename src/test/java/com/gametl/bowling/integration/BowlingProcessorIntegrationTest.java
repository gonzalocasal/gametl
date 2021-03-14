package com.gametl.bowling.integration;

import com.gametl.bowling.processor.BowlingProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BowlingProcessorIntegrationTest {

  @Autowired
  private BowlingProcessor bowlingProcessor;


  @Test
  void bowlingGameProcessingTest() throws Exception {
    bowlingProcessor.process();
  }

}