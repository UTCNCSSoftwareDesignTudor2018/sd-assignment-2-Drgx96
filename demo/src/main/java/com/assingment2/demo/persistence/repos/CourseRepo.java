package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Integer> {

    public List<Course> findAllBySubject(String subject);
}
