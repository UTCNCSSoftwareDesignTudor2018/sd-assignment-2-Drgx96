package com.assingment2.demo.rest;

import com.assingment2.demo.persistence.entities.Course;
import com.assingment2.demo.persistence.entities.Enrollment;
import com.assingment2.demo.persistence.entities.Grade;
import com.assingment2.demo.rest.dtos.CourseInfo;
import com.assingment2.demo.rest.dtos.EnrollmentInfo;
import com.assingment2.demo.rest.dtos.GradeInfo;
import com.assingment2.demo.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Inject
    CourseService courseService;

    @PostMapping
    public ResponseEntity<?> add(@RequestParam("teacherId") Integer teacherId, @RequestBody CourseInfo courseInfo) {
        Course course = courseInfo.toCourse();
        course.setId(null);
        Course created = this.courseService.createCourseForTeacher(course, teacherId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/grades")
    public ResponseEntity<?> add(@RequestParam("userId") Integer userId, @RequestParam("courseId") Integer courseId, @RequestBody Grade grade) {
        grade.setId(null);
        this.courseService.addGrade(userId, courseId, grade);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/enroll")
    public ResponseEntity<?> add(@RequestParam("userId") Integer userId, @RequestParam("courseId") Integer courseId) {
        this.courseService.enroll(userId, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<CourseInfo> getCourses() {
        return courseService.getAllCourses().stream().map(CourseInfo::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/enrollments")
    public List<CourseInfo> getEnrollmentsOfUser(@RequestParam("studentId") Integer studentId) {
        return courseService.getCoursesOfUser(studentId).stream().map(CourseInfo::new).collect(Collectors.toList());
    }

    @PostMapping(path = "/grade")
    public ResponseEntity<?> gradeStudentAtCourse(@RequestParam("studentId") Integer studentId,@RequestParam("courseId") Integer courseId, @RequestBody GradeInfo grade)
    {
        courseService.addGrade(studentId, courseId, grade.toGrade());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/grades")
    public Set<EnrollmentInfo> getStudentsGrades(@RequestParam("studentId") Integer studentId)
    {
        return courseService.getStudentGrades(studentId).stream().map(EnrollmentInfo::new).collect(Collectors.toSet());
    }
}
