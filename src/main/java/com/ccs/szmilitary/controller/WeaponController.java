package com.ccs.szmilitary.controller;

import com.ccs.szmilitary.domain.WeaponDomain;
import com.ccs.szmilitary.domain.WeaponDomainToReturn;
import com.ccs.szmilitary.service.WeaponService;
import com.ccs.szmilitary.util.PNGMapUtil;
import main.java.com.UpYun;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2015/7/2.
 */
@Controller
public class WeaponController {
    @Autowired
    private WeaponService weaponService;

    @RequestMapping(value = "/api/weapon/all")
    public  @ResponseBody Object getAllWeapon(){
        List<WeaponDomain> weaponDomains =  weaponService.getAll();
        List<WeaponDomainToReturn> weaponDomainToReturns = new ArrayList<WeaponDomainToReturn>();
        for (WeaponDomain weaponDomain : weaponDomains){
            WeaponDomainToReturn weaponDomainToReturn = new WeaponDomainToReturn();
            BeanUtils.copyProperties(weaponDomain,weaponDomainToReturn);
            weaponDomainToReturn.setCountry_img(PNGMapUtil.country_name_img_map().get(weaponDomainToReturn.getWeapon_country()));
            weaponDomainToReturns.add(weaponDomainToReturn);
        }
        return weaponDomainToReturns;
    }

    @RequestMapping(value = "/api/weapon/search")
    public @ResponseBody Object searchWeapon(HttpServletRequest request){

        String cat = request.getParameter("cat");
        String val = request.getParameter("val");
        List<WeaponDomain> weaponDomains = new ArrayList<WeaponDomain>();
        List<WeaponDomainToReturn> weaponDomainToReturns = new ArrayList<WeaponDomainToReturn>();
        if ("weapon_name".equals(cat)){
            weaponDomains = weaponService.searchWeaponByName(val);
        }else if ("weapon_country".equals(cat)){
            weaponDomains = weaponService.searchWeaponByCountry(val);
        }else if ("weapon_category".equals(cat)){
            weaponDomains = weaponService.searchWeaponByCategory(val);
        }
        for (WeaponDomain weaponDomain : weaponDomains){
            WeaponDomainToReturn weaponDomainToReturn = new WeaponDomainToReturn();
            BeanUtils.copyProperties(weaponDomain,weaponDomainToReturn);
            weaponDomainToReturn.setCountry_img(PNGMapUtil.country_name_img_map().get(weaponDomainToReturn.getWeapon_country()));
            weaponDomainToReturns.add(weaponDomainToReturn);
        }
        return weaponDomainToReturns;
    }

    @RequestMapping(value = "/api/weapon/{weapon_id}/webgl")
    public String getWebGL(@PathVariable(value = "weapon_id") String weapon_id,Model model,HttpServletRequest request){
        String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/webgl/tmp/texture";
        File filemake = new File(filePath);
        if(!filemake.exists()){
            try {
                filemake.mkdirs();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        WeaponDomain weaponDomain = weaponService.getWeaponById(Integer.parseInt(weapon_id));
        if (!weaponDomain.getWeapon_texture().equals("")){//说明贴图文件不为空
            model.addAttribute("texture_path",weaponDomain.getWeapon_texture());
        }
        if (!weaponDomain.getWeapon_model().equals("")){//模型文件不为空
            model.addAttribute("model_path",weaponDomain.getWeapon_model());
        }
        return "/webgl/webgl";
    }

    @RequestMapping(value = "/api/test/touch")
    public String touch(){
        return "/webgl/touch";
    }
}
