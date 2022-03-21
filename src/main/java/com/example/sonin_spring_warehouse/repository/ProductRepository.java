package com.example.sonin_spring_warehouse.repository;

import com.example.sonin_spring_warehouse.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Optional<Product> findByName(String name);
    List<Product> findByNameIgnoreCaseContaining(String partOfName);
    List<Product> findByNameIgnoreCaseContainingAndQuantityGreaterThan(String partOfName, int q);
    List<Product> findByPriceBetween(int min, int max);
    List<Product> findByQuantityGreaterThanAndPriceBetween(int q, int min, int max);
    List<Product> findByQuantityGreaterThan(int q);
    List<Product> findByQuantityGreaterThanEqual(int q);
    List<Product> findByQuantityEquals (int q);
}
