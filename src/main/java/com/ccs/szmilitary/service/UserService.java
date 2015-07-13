package com.ccs.szmilitary.service;

import com.ccs.szmilitary.dao.UserMapper;
import com.ccs.szmilitary.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhang on 2015/7/13.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void addUser(UserDomain userDomain){
        userMapper.addUser(userDomain);
    }

    public UserDomain getUserByUsername(String username){
        return userMapper.getUserByUsername(username);
    }
}
