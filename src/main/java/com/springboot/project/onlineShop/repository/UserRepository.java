package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
