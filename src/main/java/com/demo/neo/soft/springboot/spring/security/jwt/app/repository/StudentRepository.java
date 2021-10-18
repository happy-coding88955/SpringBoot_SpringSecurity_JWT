package com.demo.neo.soft.springboot.spring.security.jwt.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,String>{

	
	Optional<Student> findByFirstName(String username);
}
