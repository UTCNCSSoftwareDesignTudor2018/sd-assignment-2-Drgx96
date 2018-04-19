package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.Address;
import com.assingment2.demo.persistence.entities.User;

public abstract class UserInfo {
    public Integer id;

    public String firstName;

    public String lastName;

    public String phone;

    public String email;

    public String identityCardNumber;

    public Address address;

    protected UserInfo(User user)
    {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        address = user.getAddress();
        email = user.getEmail();
        identityCardNumber = user.getIdentityCardNumber();
        phone = user.getPhone();
    }

    public UserInfo() {

    }

    public User toUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setEmail(email);
        user.setIdentityCardNumber(identityCardNumber);
        user.setPhone(phone);
        user.setAdmin(false);
        return user;
    }
}
