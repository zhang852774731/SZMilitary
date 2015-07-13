package com.ccs.szmilitary.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhang on 2015/7/13.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String username = (String)authenticationToken.getPrincipal();
        AtomicInteger atomicInteger = passwordRetryCache.get(username);
        if (atomicInteger == null){
            atomicInteger = new AtomicInteger(0);
            passwordRetryCache.put(username,atomicInteger);
        }
        if (atomicInteger.incrementAndGet() > 5){
            throw new ExcessiveAttemptsException("您好，您的密码输入错误超过5次，暂时被锁定");
        }
        boolean matches = super.doCredentialsMatch(authenticationToken,authenticationInfo);
        if (matches){
            passwordRetryCache.clear();
        }
        return matches;
    }
}
