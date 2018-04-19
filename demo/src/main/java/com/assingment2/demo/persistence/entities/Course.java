package com.assingment2.demo.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String subject;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    User professor;

    @OneToMany(mappedBy = "primaryKey.course",
            cascade = CascadeType.ALL,
            targetEntity = Enrollment.class)
    Set<Enrollment> enrollments = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "course[" + id + "] " + subject + " ---> " + enrollments;
    }


}
