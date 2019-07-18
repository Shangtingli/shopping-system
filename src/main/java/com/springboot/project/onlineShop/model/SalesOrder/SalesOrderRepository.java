package com.springboot.project.onlineShop.model.SalesOrder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder,Long> {
}