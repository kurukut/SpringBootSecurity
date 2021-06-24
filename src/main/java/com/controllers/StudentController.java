package com.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Student;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private static final List<Student>STUDENTS=Arrays.asList(
			new Student(1, "Kuru"),
			new Student(2,"Murumon")
			);
	@GetMapping("/studentId/{studentId}")
	public Student getUser(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
		.filter(student->studentId.equals(student.getStudentId()))
		.findFirst()
		.orElseThrow(()->new IllegalStateException("no such user"));
	}
	

}
