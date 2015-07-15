package com.ccs.szmilitary.domain;

import java.io.Serializable;

/**
 * Created by zhang on 2015/7/15.
 */
public class BroadcastWeaponDomain implements Serializable{
    private int weapon_id;
    private String weapon_attr;

    public String getWeapon_attr() {
        return weapon_attr;
    }

    public void setWeapon_attr(String weapon_attr) {
        this.weapon_attr = weapon_attr;
    }

    public int getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }
}
