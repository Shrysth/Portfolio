package com.portfolio.Shrysth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ShrysthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShrysthApplication.class, args);
	}

}
