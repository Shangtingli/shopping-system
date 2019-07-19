package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.SalesOrder;
import com.springboot.project.onlineShop.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Override
    public void addSalesOrder(SalesOrder salesOrder) {
        salesOrderRepository.save(salesOrder);
    }
}
