package com.assingment2.demo.services;


import com.assingment2.demo.persistence.entities.Course;
import com.assingment2.demo.persistence.entities.Enrollment;
import com.assingment2.demo.persistence.entities.Grade;
import com.assingment2.demo.persistence.entities.User;
import com.assingment2.demo.persistence.repos.CourseRepo;
import com.assingment2.demo.persistence.repos.EnrollmentRepo;
import com.assingment2.demo.persistence.repos.GradeRepo;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service()
public class CourseService {

    @Inject
    UserService userService;
    @Inject
    CourseRepo courseRepository;
    @Inject
    GradeRepo gradeRepository;
    @Inject
    EnrollmentRepo enrollmentRepo;
    @PersistenceContext
    private EntityManager em;

    CourseService() {

    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesBySubject(String subject) {
        List<Course> coursesFilteredList = null;

        if (StringUtils.isEmpty(subject)) {
            coursesFilteredList = courseRepository.findAll();
        } else {
            coursesFilteredList = courseRepository.findAllBySubject(subject);
        }

        return coursesFilteredList;
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Set<Grade> addGrade(Integer userId, Integer courseId, Grade grade) {
        User user = userService.getStudent(userId);
        Enrollment enrollment = user.getEnrollments().stream().filter(x -> x.getCourse().getId()==courseId).findFirst().get();
        enrollment.getGrades().add(grade);
        return enrollmentRepo.save(enrollment).getGrades();
    }

    public Set<Course> getCoursesOfUser(Integer userId) {
        User user = userService.getStudent(userId);
        return user.getEnrollments().stream().map(x -> x.getCourse()).distinct().collect(Collectors.toSet());
    }

    public Enrollment enroll(Integer userId, Integer courseId) {
        Session session = em.unwrap(Session.class);
        Course course = session.load(Course.class, courseId);
        User user = session.load(User.class, userId);
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        return enrollmentRepo.save(enrollment);
    }

    public Course createCourseForTeacher(Course course, Integer teacherId) {
        Session session = em.unwrap(Session.class);
        User user = session.load(User.class, teacherId);
        course.setProfessor(user);
        return courseRepository.save(course);
    }

    public Set<Enrollment> getStudentGrades(Integer studentId) {
        return userService.getStudent(studentId).getEnrollments();
    }
}
