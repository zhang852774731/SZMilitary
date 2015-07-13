package com.ccs.szmilitary.realm;

import com.ccs.szmilitary.domain.UserDomain;
import com.ccs.szmilitary.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhang on 2015/7/13.
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("asdf");
        System.out.println("asdf");
        System.out.println("asdf");
        System.out.println("asdf");
        System.out.println("asdf");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        UserDomain userDomain = userService.getUserByUsername(username);
        if (userDomain == null){
            throw new UnknownAccountException("对不起，没有该账户信息");
        }

        if (Boolean.TRUE.equals(userDomain.getLocked())){
            throw new LockedAccountException("您好，你的账户已被锁定");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userDomain.getUsername(),
                userDomain.getPassword(),
                ByteSource.Util.bytes(userDomain.getSalt()),
                getName()
        );
        return simpleAuthenticationInfo;
    }
}
