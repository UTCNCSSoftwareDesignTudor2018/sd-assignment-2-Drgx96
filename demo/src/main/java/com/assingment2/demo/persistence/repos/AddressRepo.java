package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
