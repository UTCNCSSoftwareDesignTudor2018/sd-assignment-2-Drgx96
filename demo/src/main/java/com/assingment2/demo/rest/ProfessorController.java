package com.assingment2.demo.rest;

import com.assingment2.demo.persistence.entities.User;
import com.assingment2.demo.rest.dtos.ProfessorInfo;
import com.assingment2.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class ProfessorController {

    @Inject
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody ProfessorInfo professorInfo) {
        User user = professorInfo.toUser();
        user.setAdmin(true);
        user.setId(null);
        User created = this.userService.create(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<ProfessorInfo> getTeachers() {
        return userService.getAllTeachers().stream().map(ProfessorInfo::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/teacher")
    public ProfessorInfo getProfessor(@RequestParam Integer id) {
        return new ProfessorInfo(userService.getTeacher(id));
    }

    @PatchMapping(value = "/teacher")
    public ProfessorInfo patchProfessor(@RequestParam Integer id, @RequestBody ProfessorInfo professorInfo) {
        User user = professorInfo.toUser();
        user.setId(id);
        user.setAdmin(true);
        return new ProfessorInfo(userService.updateUser(user));
    }
}
