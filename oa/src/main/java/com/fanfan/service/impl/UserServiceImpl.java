package com.fanfan.service.impl;

import com.fanfan.mapper.UserMapper;
import com.fanfan.pojo.User;
import com.fanfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }
}
