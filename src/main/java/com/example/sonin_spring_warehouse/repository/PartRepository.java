package com.example.sonin_spring_warehouse.repository;

import com.example.sonin_spring_warehouse.model.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends CrudRepository<Part, Integer> {
    Part findByCode(String code);
}
