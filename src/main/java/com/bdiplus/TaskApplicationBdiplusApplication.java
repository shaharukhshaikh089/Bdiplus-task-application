package com.bdiplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@SpringBootApplication
public class TaskApplicationBdiplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplicationBdiplusApplication.class, args);
	}

}
