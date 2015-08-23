package com.ccs.szmilitary.controller;

import com.ccs.szmilitary.domain.*;
import com.ccs.szmilitary.service.BroadcastService;
import com.ccs.szmilitary.service.WeaponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private WeaponService weaponService;

    @RequestMapping(value = "/api/broadcast/all")
    public @ResponseBody Object getAllBroadcast(){
        return broadcastService.getAllBroadcast();
    }

    @RequestMapping(value = "/api/broadcast/add",method = RequestMethod.POST)
    public @ResponseBody void addBroadcast(@RequestBody BroadcastDomain broadcastDomain){
        List<BroadcastItemDomain> itemDomainsFromDB = broadcastService.getBroadcastByName(broadcastDomain.getBroadcast_name());
        if (itemDomainsFromDB != null){//说明这个串联单已经存在，那么删除已经存在的
            broadcastService.deleteBroadcastByName(broadcastDomain.getBroadcast_name());
        }
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
                broadcastItemDomain.setTroops(broadcastWeaponDomain.getTroops());
                i++;
                broadcastItemDomains.add(broadcastItemDomain);
            }
        }
        broadcastService.addBroadcastBatch(broadcastItemDomains);
    }

    @RequestMapping(value = "/api/broadcast/get/{broadcast_name}")
    public @ResponseBody Object getBroadcastByName(@PathVariable(value = "broadcast_name") String broadcast_name){
        List<BroadcastWeaponToReturn> weaponDomains = new ArrayList<BroadcastWeaponToReturn>();
        List<BroadcastItemDomain> broadcastItemDomains = broadcastService.getBroadcastByName(broadcast_name);
        if (broadcastItemDomains != null && broadcastItemDomains.size()>0){
            for (BroadcastItemDomain broadcastItemDomain : broadcastItemDomains){
                BroadcastWeaponToReturn broadcastWeaponToReturn = new BroadcastWeaponToReturn();
                WeaponDomain weaponDomain = weaponService.getWeaponById(broadcastItemDomain.getWeapon_id());
                weaponDomain.setWeapon_attr(broadcastItemDomain.getWeapon_attr());//修改为选中的属性
                BeanUtils.copyProperties(weaponDomain, broadcastWeaponToReturn);
                broadcastWeaponToReturn.setTroops(broadcastItemDomain.getTroops());
                weaponDomains.add(broadcastWeaponToReturn);
            }
        }
        return weaponDomains;
    }

    @RequestMapping(value = "/api/broadcast/delete/{broadcast_name}")
    public void deleteBroadcastByName(@PathVariable(value = "broadcast_name") String broadcast_name){
        broadcastService.deleteBroadcastByName(broadcast_name);
    }

}
