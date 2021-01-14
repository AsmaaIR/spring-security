package com.springsecurity.demo.service;

import com.springsecurity.demo.dto.CustomUser;
import com.springsecurity.demo.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CustomUser customUser);
}
