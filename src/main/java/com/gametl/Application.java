package com.gametl;

import com.gametl.bowling.in.FileBowlingBuilder;
import com.gametl.bowling.model.BowlingGame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public Application(FileBowlingBuilder fileReader) {
		this.fileReader = fileReader;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final FileBowlingBuilder fileReader;

	@Override
	public void run(String... args) throws Exception {
		BowlingGame build = fileReader.build("/Users/gonzalo/Downloads/bowling");
		build.getResults();
	}

}