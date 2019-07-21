package com.springboot.project.onlineShop.service;

import com.springboot.project.onlineShop.model.Authorities;
import com.springboot.project.onlineShop.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public void addAuthorities(Authorities authorities) {

        authoritiesRepository.save(authorities);
        System.out.println("Hello");
    }
}
