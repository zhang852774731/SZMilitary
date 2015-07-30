package com.ccs.szmilitary.service;

import com.ccs.szmilitary.dao.WeaponMapper;
import com.ccs.szmilitary.domain.WeaponDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang on 2015/7/2.
 */
@Service
@Transactional
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

    public List<WeaponDomain> searchWeaponByName(String val){
        return weaponMapper.searchWeaponByName(val);
    }
    public List<WeaponDomain> searchWeaponByCountry(String val){
        return weaponMapper.searchWeaponByCountry(val);
    }

    public List<WeaponDomain> searchWeaponByCategory(String val){
        return weaponMapper.searchWeaponByCategory(val);
    }
}
