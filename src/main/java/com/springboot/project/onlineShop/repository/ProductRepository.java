package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
}