package com.gametl;

import com.gametl.bowling.processor.BowlingProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public Application(BowlingProcessor bowlingProcessor) {
		this.bowlingProcessor = bowlingProcessor;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final BowlingProcessor bowlingProcessor;

	@Override
	public void run(String... args) {
		bowlingProcessor.process("/Users/gonzalo/Downloads/bowling");
	}

}