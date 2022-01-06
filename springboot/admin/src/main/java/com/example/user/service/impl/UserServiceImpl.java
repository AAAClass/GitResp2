package com.example.user.service.impl;


import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }
}
