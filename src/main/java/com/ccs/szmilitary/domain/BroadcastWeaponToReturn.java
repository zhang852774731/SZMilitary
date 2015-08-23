package com.ccs.szmilitary.domain;

import java.io.Serializable;

/**
 * Created by zhangbin on 15/8/21.
 */
public class BroadcastWeaponToReturn extends WeaponDomain implements Serializable{
    private String troops;

    public String getTroops() {
        return troops;
    }

    public void setTroops(String troops) {
        this.troops = troops;
    }
}
