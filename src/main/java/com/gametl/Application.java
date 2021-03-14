package com.gametl;

import com.gametl.bowling.processor.BowlingProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



	private final BowlingProcessor processor;

	public Application(BowlingProcessor processor) {
		this.processor = processor;
	}


	@Override
	public void run(String... args) throws Exception {
		processor.process();
	}

}