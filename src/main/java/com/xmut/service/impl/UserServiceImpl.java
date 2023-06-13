package com.xmut.service.impl;

import com.xmut.mapper.UserMapper;
import com.xmut.pojo.User;
import com.xmut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date: 2023/4/25
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {

        return userMapper.findUserByEmailAndPassword(user);
    }
}
