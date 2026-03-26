package com.davih.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

	@Autowired
	private StudentService studentService;

	public void test() {
		System.out.println("teacher test: " + studentService);
	}
}
