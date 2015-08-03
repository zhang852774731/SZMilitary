package com.ccs.szmilitary.domain;

import java.io.Serializable;

/**
 * Created by zhang on 2015/8/3.
 */
public class WeaponDomainToReturn extends WeaponDomain implements Serializable{
    private String country_img;

    public String getCountry_img() {
        return country_img;
    }

    public void setCountry_img(String country_img) {
        this.country_img = country_img;
    }
}
