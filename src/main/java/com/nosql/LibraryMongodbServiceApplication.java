package com.nosql;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class LibraryMongodbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMongodbServiceApplication.class, args);
	}

}
