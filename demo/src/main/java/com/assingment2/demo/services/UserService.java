package com.assingment2.demo.services;

import com.assingment2.demo.persistence.entities.User;
import com.assingment2.demo.persistence.repos.UserRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Inject
    UserRepo userRepo;

    public List<User> getAllTeachers() {
        return userRepo.findAll().stream().filter(user -> user.getAdmin()).collect(Collectors.toList());
    }

    public User create(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllStudents() {
        return userRepo.findAll().stream().filter(user -> !user.getAdmin()).collect(Collectors.toList());
    }

    public User getStudent(Integer userId) {
        User u = userRepo.getOne(Integer.valueOf(userId));
        if (!u.getAdmin())
            return u;
        else
            return null;
    }

    public User getTeacher(Integer userId) {
        User u = userRepo.getOne(Integer.valueOf(userId));
        if (u.getAdmin())
            return u;
        else
            return null;
    }

    public User updateUser(User user) {
        User existing = userRepo.getOne(user.getId());
        if(user.getEnrollments()!=null)
        {
            existing.setCourses(user.getEnrollments());
        }
        if(user.getAdmin()!=null)
        {
            existing.setAdmin(user.getAdmin());
        }
        if(user.getAddress()!=null)
        {
            existing.setAddress(user.getAddress());
        }
        if(user.getEmail()!=null)
        {
            existing.setEmail(user.getEmail());
        }
        if(user.getFirstName()!=null)
        {
            existing.setFirstName(user.getFirstName());
        }
        if(user.getIdentityCardNumber()!=null)
        {
            existing.setIdentityCardNumber(user.getIdentityCardNumber());
        }
        if(user.getLastName()!=null)
        {
            existing.setLastName(user.getLastName());
        }
        if(user.getPhone()!=null)
        {
            existing.setPhone(user.getPhone());
        }
        return userRepo.save(existing);
    }
}
