package com.ccs.szmilitary.dao;

import com.ccs.szmilitary.domain.BroadcastDomain;
import com.ccs.szmilitary.domain.BroadcastItemDomain;

import java.util.List;

/**
 * Created by zhang on 2015/7/15.
 */
public interface BroadcastMapper {
    public List<String> getAllBroadcast();
    public List<BroadcastItemDomain> getBroadcastByName(String broadcastname);
    public void addBroadcastBatch(List<BroadcastItemDomain> broadcastItemDomains);
}
