package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    public List<User> findAllByFirstName(String firstName);
}
