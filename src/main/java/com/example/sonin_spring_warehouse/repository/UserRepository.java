package com.example.sonin_spring_warehouse.repository;

import com.example.sonin_spring_warehouse.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Customer, Integer> {

}
