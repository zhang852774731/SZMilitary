package com.ccs.szmilitary.domain;

import java.io.Serializable;

/**
 * Created by zhang on 2015/7/15.
 * 保存到数据库中的模型
 */
public class BroadcastItemDomain implements Serializable{
    private String broadcast_name;
    private String broadcast_ctime;
    private int weapon_id;
    private int weapon_order;
    private String weapon_attr;
    private String troops;

    public String getBroadcast_name() {
        return broadcast_name;
    }

    public void setBroadcast_name(String broadcast_name) {
        this.broadcast_name = broadcast_name;
    }

    public String getBroadcast_ctime() {
        return broadcast_ctime;
    }

    public void setBroadcast_ctime(String broadcast_ctime) {
        this.broadcast_ctime = broadcast_ctime;
    }

    public int getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }

    public int getWeapon_order() {
        return weapon_order;
    }

    public void setWeapon_order(int weapon_order) {
        this.weapon_order = weapon_order;
    }

    public String getWeapon_attr() {
        return weapon_attr;
    }

    public void setWeapon_attr(String weapon_attr) {
        this.weapon_attr = weapon_attr;
    }

    public String getTroops() {
        return troops;
    }

    public void setTroops(String troops) {
        this.troops = troops;
    }
}
