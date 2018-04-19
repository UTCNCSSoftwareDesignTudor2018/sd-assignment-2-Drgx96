package com.assingment2.demo.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String phone;

    @Column
    String email;

    @Column
    Boolean admin;

    @Column
    String identityCardNumber;

    @OneToMany(mappedBy = "primaryKey.user",
            cascade = CascadeType.ALL,
            targetEntity = Enrollment.class)
    Set<Enrollment> enrollments = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    Group group;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setCourses(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "teacher[" + id + "] " + firstName + " " + lastName;
    }
}