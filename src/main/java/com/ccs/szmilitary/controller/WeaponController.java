package com.ccs.szmilitary.controller;

import com.ccs.szmilitary.domain.WeaponDomain;
import com.ccs.szmilitary.service.WeaponService;
import main.java.com.UpYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * Created by zhang on 2015/7/2.
 */
@Controller
public class WeaponController {
    @Autowired
    private WeaponService weaponService;
    @Autowired
    private UpYun upYun;

    @RequestMapping(value = "/api/weapon/all")
    public  @ResponseBody Object getAllWeapon(){
        List<WeaponDomain> weaponDomains =  weaponService.getAll();
        return weaponDomains;
    }

    @RequestMapping(value = "/api/weapon/{weapon_id}/webgl")
    public String getWebGL(@PathVariable(value = "weapon_id") String weapon_id,Model model){
        WeaponDomain weaponDomain = weaponService.getWeaponById(Integer.parseInt(weapon_id));
        if (!weaponDomain.getWeapon_texture().equals("http://ccssz.b0.upaiyun.com")){//说明贴图文件不为空
            String[] parse_texture_path = weaponDomain.getWeapon_texture().split("/");
            String texture_name = parse_texture_path[parse_texture_path.length-1];
            File textureFile = new File("/resources/webgl/tmp/texture/");
            String tmp_texture = "/resources/webgl/tmp/texture/"+texture_name;
            String suffix = texture_name.substring(texture_name.lastIndexOf(".")+1);
            try{
                File.createTempFile("texture",suffix,textureFile);
            }catch (Exception e){
                e.printStackTrace();
            }
            boolean result = upYun.readFile(weaponDomain.getWeapon_texture(),textureFile);
            if (result){
                model.addAttribute("texture_path",tmp_texture);
            }
        }
        if (!weaponDomain.getWeapon_model().equals("http://ccssz.b0.upaiyun.com")){//模型文件不为空
            model.addAttribute("model_path",weaponDomain.getWeapon_texture());
        }
        return "/webgl/webgl";
    }
}
