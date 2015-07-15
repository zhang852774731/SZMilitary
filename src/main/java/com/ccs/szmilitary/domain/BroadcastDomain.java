package com.ccs.szmilitary.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang on 2015/7/15.
 */
public class BroadcastDomain implements Serializable{
    private String broadcast_name;
    private String broadcast_ctime;
    private List<BroadcastWeaponDomain> broadcast_order;

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

    public List<BroadcastWeaponDomain> getBroadcast_order() {
        return broadcast_order;
    }

    public void setBroadcast_order(List<BroadcastWeaponDomain> broadcast_order) {
        this.broadcast_order = broadcast_order;
    }
}
