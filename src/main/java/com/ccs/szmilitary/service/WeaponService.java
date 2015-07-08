package com.ccs.szmilitary.service;

import com.ccs.szmilitary.dao.WeaponMapper;
import com.ccs.szmilitary.domain.WeaponDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2015/7/2.
 */
@Service
public class WeaponService {
    @Autowired
    private WeaponMapper weaponMapper;

    public List<WeaponDomain> getAll(){
        return weaponMapper.getAll();
    }

    public WeaponDomain getWeaponById(int weaponid){return weaponMapper.getWeaponById(weaponid);}

    public void addWeapon(WeaponDomain weaponDomain){
        weaponMapper.addWeapon(weaponDomain);
    }

    public void updateWeapon(WeaponDomain weaponDomain){weaponMapper.updateWeapon(weaponDomain);}

    public void delWeapon(int weaponid){weaponMapper.delWeapon(weaponid);}
}
