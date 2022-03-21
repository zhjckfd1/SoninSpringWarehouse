package com.example.sonin_spring_warehouse.repository;

import com.example.sonin_spring_warehouse.model.Customer;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByLogin(String login);
}
