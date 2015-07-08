package com.ccs.szmilitary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhang on 2015/7/6.
 */
@Component("setting")
public class Setting {
    @Value("${upyun.bucket}")
    public String upyun_bucket;

    public String getUpyun_bucket() {
        return upyun_bucket;
    }

    public void setUpyun_bucket(String upyun_bucket) {
        this.upyun_bucket = upyun_bucket;
    }
}
