package com.foo.bar.batch.data_batch_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataBatchProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBatchProcessorApplication.class, args);
	}

}
