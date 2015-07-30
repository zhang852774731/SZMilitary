package com.ccs.szmilitary.dao;

import com.ccs.szmilitary.domain.WeaponDomain;

import java.util.List;

/**
 * Created by zhang on 2015/7/2.
 */
public interface WeaponMapper {
    public List<WeaponDomain> getAll();
    public WeaponDomain getWeaponById(int weaponid);
    public void addWeapon(WeaponDomain weaponDomain);
    public void updateWeapon(WeaponDomain weaponDomain);
    public void delWeapon(int weaponid);
    public List<WeaponDomain> searchWeaponByName(String val);
    public List<WeaponDomain> searchWeaponByCountry(String val);
    public List<WeaponDomain> searchWeaponByCategory(String val);
}
