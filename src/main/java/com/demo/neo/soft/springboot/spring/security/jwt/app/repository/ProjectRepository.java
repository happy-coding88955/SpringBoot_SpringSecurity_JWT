package com.demo.neo.soft.springboot.spring.security.jwt.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

}
