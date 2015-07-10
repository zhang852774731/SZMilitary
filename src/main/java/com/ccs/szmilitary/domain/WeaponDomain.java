package com.ccs.szmilitary.domain;

import java.io.Serializable;

/**
 * Created by zhang on 2015/7/2.
 */
public class WeaponDomain implements Serializable{
    private int id;
    private String weapon_name;
    private String weapon_country;
    private String weapon_category;
    private String weapon_attr;
    private String weapon_thumbnail;
    private String weapon_model;
    private String weapon_texture;
    private String weapon_gh_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeapon_name() {
        return weapon_name;
    }

    public void setWeapon_name(String weapon_name) {
        this.weapon_name = weapon_name;
    }

    public String getWeapon_country() {
        return weapon_country;
    }

    public void setWeapon_country(String weapon_country) {
        this.weapon_country = weapon_country;
    }

    public String getWeapon_category() {
        return weapon_category;
    }

    public void setWeapon_category(String weapon_category) {
        this.weapon_category = weapon_category;
    }

    public String getWeapon_attr() {
        return weapon_attr;
    }

    public void setWeapon_attr(String weapon_attr) {
        this.weapon_attr = weapon_attr;
    }

    public String getWeapon_thumbnail() {
        return weapon_thumbnail;
    }

    public void setWeapon_thumbnail(String weapon_thumbnail) {
        this.weapon_thumbnail = weapon_thumbnail;
    }

    public String getWeapon_model() {
        return weapon_model;
    }

    public void setWeapon_model(String weapon_model) {
        this.weapon_model = weapon_model;
    }

    public String getWeapon_texture() {
        return weapon_texture;
    }

    public void setWeapon_texture(String weapon_texture) {
        this.weapon_texture = weapon_texture;
    }

    public String getWeapon_gh_path() {
        return weapon_gh_path;
    }

    public void setWeapon_gh_path(String weapon_gh_path) {
        this.weapon_gh_path = weapon_gh_path;
    }
}
