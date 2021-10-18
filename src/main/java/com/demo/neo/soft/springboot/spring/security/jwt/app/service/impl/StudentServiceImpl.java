package com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Student;
import com.demo.neo.soft.springboot.spring.security.jwt.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository repository;

	@Override
	public List<Student> getAllStudents(){
		return repository.findAll();
	}

	@Override
	public Optional<Student> getStudentsById(String studentId){
		Optional<Student> student=repository.findById(studentId);
			if (!student.isPresent()) {
			
			}
			Student student2=student.get();
		return Optional.ofNullable(student2);
	}

	public Student save(Student student) {
		return student;
	}

}
