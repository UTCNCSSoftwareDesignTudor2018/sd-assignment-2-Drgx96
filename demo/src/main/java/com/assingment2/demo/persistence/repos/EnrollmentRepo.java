package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer> {

}
