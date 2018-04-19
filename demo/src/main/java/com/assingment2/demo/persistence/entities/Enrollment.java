package com.assingment2.demo.persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "enrollments")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.user",
                joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "primaryKey.courses",
                joinColumns = @JoinColumn(name = "COURSE_ID")) })
public class Enrollment {

    EnrollmentId primaryKey = new EnrollmentId();

    Set<Grade> grades;

    @EmbeddedId
    public EnrollmentId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(EnrollmentId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public User getUser() {
        return getPrimaryKey().getUser();
    }

    public void setUser(User user) {
        getPrimaryKey().setUser(user);
    }

    @Transient
    public Course getCourse() {
        return getPrimaryKey().getCourse();
    }

    public void setCourse(Course course) {
        getPrimaryKey().setCourse(course);
    }

    @OneToMany(targetEntity = Grade.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
