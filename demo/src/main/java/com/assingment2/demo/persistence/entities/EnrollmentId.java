package com.assingment2.demo.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class EnrollmentId implements Serializable {
    private User user;
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    public User getUser()
    {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
