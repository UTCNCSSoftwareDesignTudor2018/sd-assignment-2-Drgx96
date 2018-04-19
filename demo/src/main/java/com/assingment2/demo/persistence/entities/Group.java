package com.assingment2.demo.persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String group_code;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "representing_student")
    User representing_student;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    List<User> students = new ArrayList<>();

    public Group(int id, String group_code, User representing_student) {
        super();
        this.id = id;
        this.group_code = group_code;
        this.representing_student = representing_student;
    }

    public int getId() {
        return id;
    }

    public String getGroup_code() {
        return group_code;
    }

    public User getRepresenting_student() {
        return representing_student;
    }
}