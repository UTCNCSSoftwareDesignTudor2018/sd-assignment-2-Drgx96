package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Integer> {

}
