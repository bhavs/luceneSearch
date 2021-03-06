package com.example.lucene.luceneAttempt21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EntityScan
public class LuceneAttempt21Application {

	public static void main(String[] args) {
		SpringApplication.run(LuceneAttempt21Application.class, args);
	}

}
