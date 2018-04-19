package com.assingment2.demo.rest;

import com.assingment2.demo.persistence.entities.User;
import com.assingment2.demo.rest.dtos.StudentInfo;
import com.assingment2.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Inject
    UserService userService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody StudentInfo studentInfo) {
        User created = this.userService.create(studentInfo.toUser());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/student")
    public StudentInfo getStudent(@RequestParam Integer id) {
        return new StudentInfo(userService.getStudent(id));
    }

    @GetMapping
    public List<StudentInfo> getStudents() {
        return userService.getAllStudents().stream().map(StudentInfo::new).collect(Collectors.toList());
    }


    @PatchMapping(value = "/student")
    public StudentInfo patchStudent(@RequestParam Integer id, @RequestBody StudentInfo studentInfo) {
        User user = studentInfo.toUser();
        user.setId(id);
        user.setAdmin(false);
        return new StudentInfo(userService.updateUser(user));
    }
}
