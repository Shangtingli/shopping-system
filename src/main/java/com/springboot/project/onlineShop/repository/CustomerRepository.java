package com.springboot.project.onlineShop.repository;

import com.springboot.project.onlineShop.model.Customer;
import com.springboot.project.onlineShop.repository.CustomerRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository
        extends CrudRepository<Customer, Long>, CustomerRepositoryCustom {


}