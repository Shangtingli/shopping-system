package com.springboot.project.onlineShop.model.Cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

}
