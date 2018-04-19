package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepo extends JpaRepository<Grade, Integer> {

}
