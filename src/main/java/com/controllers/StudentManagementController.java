package com.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Student;

@RestController
@RequestMapping("/management/student")
public class StudentManagementController {
	private static final List<Student>STUDENTS=Arrays.asList(
			new Student(1, "Kuru"),
			new Student(2,"Murumon")
			);
	/*
	 * linda is admin==read and write
	 * tom is admintrainee==ony read
	 */
	@GetMapping("/studentId/{studentId}")
	public Student getUser(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
		.filter(student->studentId.equals(student.getStudentId()))
		.findFirst()
		.orElseThrow(()->new IllegalStateException("no such user"));
	}
	
	@GetMapping
	public List<Student> getAllStudents(){
		return STUDENTS;
	}
	
	@PostMapping
	public void insertNewStudent(@RequestBody Student student) {
		System.out.println("insertNewStudent");
		System.out.println(student);
	}
	
	@DeleteMapping("{studentId}")
	public void deleteStudent(@PathVariable int studentId) {
		System.out.println("deleteStudent");
		System.out.println(studentId);
	}
	
	@PutMapping("{studentId}")
	public void updateStudent(@PathVariable int studentId,
			@RequestBody Student student) {
		System.out.println("updateStudent");
		System.out.println(student);
	}
}
