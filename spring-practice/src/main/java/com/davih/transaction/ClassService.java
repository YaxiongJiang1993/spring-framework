package com.davih.transaction;


public class ClassService {

	private StudentService studentService;

	public ClassService(StudentService studentService) {
		this.studentService = studentService;
		System.out.println("ClassService ... " + studentService);
	}


}
