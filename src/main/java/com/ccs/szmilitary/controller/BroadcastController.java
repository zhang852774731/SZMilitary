package com.ccs.szmilitary.controller;

import com.ccs.szmilitary.domain.BroadcastDomain;
import com.ccs.szmilitary.domain.BroadcastItemDomain;
import com.ccs.szmilitary.domain.BroadcastWeaponDomain;
import com.ccs.szmilitary.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2015/7/15.
 */
@Controller
public class BroadcastController {

    @Autowired
    private BroadcastService broadcastService;

    @RequestMapping(value = "/api/broadcast/all")
    public @ResponseBody Object getAllBroadcast(){
        return broadcastService.getAllBroadcast();
    }

    @RequestMapping(value = "/api/broadcast/add",method = RequestMethod.POST)
    public @ResponseBody void addBroadcast(@RequestBody BroadcastDomain broadcastDomain){
        List<BroadcastWeaponDomain> broadcastWeaponDomains = broadcastDomain.getBroadcast_order();
        List<BroadcastItemDomain> broadcastItemDomains = new ArrayList<BroadcastItemDomain>();
        if (broadcastWeaponDomains != null && broadcastWeaponDomains.size()>0){
            int i = 1;
            for (BroadcastWeaponDomain broadcastWeaponDomain : broadcastWeaponDomains){
                BroadcastItemDomain broadcastItemDomain = new BroadcastItemDomain();
                broadcastItemDomain.setBroadcast_name(broadcastDomain.getBroadcast_name());
                broadcastItemDomain.setBroadcast_ctime(broadcastDomain.getBroadcast_ctime());
                broadcastItemDomain.setWeapon_id(broadcastWeaponDomain.getWeapon_id());
                broadcastItemDomain.setWeapon_attr(broadcastWeaponDomain.getWeapon_attr());
                broadcastItemDomain.setWeapon_order(i);
                i++;
                broadcastItemDomains.add(broadcastItemDomain);
            }
        }
        broadcastService.addBroadcastBatch(broadcastItemDomains);
    }

    @RequestMapping(value = "/api/broadcast/get/{broadcast_name}")
    public @ResponseBody Object getBroadcastByName(@PathVariable(value = "broadcast_name") String broadcast_name){
        return broadcastService.getBroadcastByName(broadcast_name);
    }
}
