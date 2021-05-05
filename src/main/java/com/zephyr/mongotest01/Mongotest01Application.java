package com.zephyr.mongotest01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Mongotest01Application {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication();

		app.run(Mongotest01Application.class, args);


	}

}
