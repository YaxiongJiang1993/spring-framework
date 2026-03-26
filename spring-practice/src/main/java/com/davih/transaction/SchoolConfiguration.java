package com.davih.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfiguration {

	@Bean
	public ClassService classService() {
		StudentService studentService = studentService();
		System.out.println("SchoolConfiguration classService: " + studentService);
		return new ClassService(studentService);
	}

	@Bean
	public StudentService studentService() {
		return new StudentService();
	}
}
