package edu.depaul.cdm.se452.se452demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class App {

	@Value("${environment}")
	private String env;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner showLogLevel() {
		return (args) -> {
			System.out.println(env);
			log.debug("Debug");
			log.info("Info");
			log.warn("Warning");
			log.error("Error");
		};
	}

}
