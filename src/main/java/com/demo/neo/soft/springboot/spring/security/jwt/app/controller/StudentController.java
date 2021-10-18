package com.demo.neo.soft.springboot.spring.security.jwt.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Student;
import com.demo.neo.soft.springboot.spring.security.jwt.app.model.AuthToken;
import com.demo.neo.soft.springboot.spring.security.jwt.app.model.JwtUserDetails;
import com.demo.neo.soft.springboot.spring.security.jwt.app.model.LoginRequest;
import com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl.StudentServiceImpl;
import com.demo.neo.soft.springboot.spring.security.jwt.app.util.JwtUtils;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth/student")
public class StudentController {
	
	
	    @Autowired
	    private StudentServiceImpl serviceImpl;
	    
	    @Autowired
	    private JwtUtils jwtUtils;

	    @PostMapping("/register")
	    @ResponseStatus(HttpStatus.CREATED)
	    public AuthToken register(@RequestBody Student student) {
	        return jwtUtils.generateToken(serviceImpl.save(student));
	    }

	    @PostMapping("/login")
	    @ResponseStatus(HttpStatus.OK)
	    public AuthToken login(@RequestBody LoginRequest loginDto) {
	       Optional<Student> userOpt = serviceImpl.getStudentsById(loginDto.getUserName());
	        if (userOpt.isPresent()) {
	            Student existingUser = userOpt.get();
	            if (loginDto.getPassword().equals(existingUser.getPassword())) {
	                return jwtUtils.generateToken(serviceImpl.save(existingUser));
	            }
	        }

	        throw new RuntimeException("UserName not exist");
	    }
	    @GetMapping("/students")
	    public List<Student> getUsers() {
	        return serviceImpl.getAllStudents();
	    }

	    @GetMapping("/students/{studentId}")
	    public Optional<Student> getUsersByStudentId(@PathVariable String studentId) {
	        return serviceImpl.getStudentsById(studentId);
	    }
	  

		@GetMapping("/validate")
	    public JwtUserDetails validate(@RequestHeader("x-auth-token") String authToken) {
	        return jwtUtils.validateToken(authToken);
	    }


	}