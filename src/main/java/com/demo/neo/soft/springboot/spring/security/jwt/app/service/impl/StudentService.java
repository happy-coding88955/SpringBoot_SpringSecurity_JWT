package com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl;

import java.util.List;
import java.util.Optional;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Student;

public interface StudentService {

	
	public List<Student> getAllStudents();
		
	
	Optional<Student> getStudentsById(String studentId);
	
}
