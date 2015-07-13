package com.ccs.szmilitary.dao;

import com.ccs.szmilitary.domain.UserDomain;

/**
 * Created by zhang on 2015/7/13.
 */
public interface UserMapper {
    public void addUser(UserDomain userDomain);
    public UserDomain getUserByUsername(String username);
}
