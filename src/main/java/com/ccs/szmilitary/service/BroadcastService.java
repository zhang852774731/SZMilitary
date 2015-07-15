package com.ccs.szmilitary.service;

import com.ccs.szmilitary.dao.BroadcastMapper;
import com.ccs.szmilitary.domain.BroadcastDomain;
import com.ccs.szmilitary.domain.BroadcastItemDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2015/7/15.
 */
@Service
public class BroadcastService {
    @Autowired
    private BroadcastMapper broadcastMapper;

    public List<String> getAllBroadcast(){
        return broadcastMapper.getAllBroadcast();
    }

    public List<BroadcastItemDomain> getBroadcastByName(String broadcastname){
        return broadcastMapper.getBroadcastByName(broadcastname);
    }

    public void addBroadcastBatch(List<BroadcastItemDomain> broadcastItemDomains){
        broadcastMapper.addBroadcastBatch(broadcastItemDomains);
    }
}
