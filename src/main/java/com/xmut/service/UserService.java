package com.xmut.service;

import com.xmut.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date: 2023/4/25
 **/
public interface UserService {

    //登陆
    public User login(User user);
}
