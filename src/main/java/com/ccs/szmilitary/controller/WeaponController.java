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

    @RequestMapping(value = "/api/weapon/webgl")
    public String getWebGL(Model model){
//        WeaponDomain weaponDomain = weaponService.getWeaponById(Integer.parseInt(weapon_id));
//        if (!weaponDomain.getWeapon_model().equals("v1.api.upyun.com/ccssz")){//说明模型文件不为空
//            String[] parse_model_path = weaponDomain.getWeapon_model().split("/");
//            String model_name = parse_model_path[parse_model_path.length-1];
//            String tmp_model = "/resources/webgl/tmp/model/"+model_name;
//            File modelFile = new File(tmp_model);
//            boolean result = upYun.readFile(weaponDomain.getWeapon_model(),modelFile);
//            if (result){
//                model.addAttribute("model_path",tmp_model);
//            }
//        }
//        if (!weaponDomain.getWeapon_texture().equals("v1.api.upyun.com/ccssz")){//说明贴图文件不为空
//            String[] parse_texture_path = weaponDomain.getWeapon_texture().split("/");
//            String texture_name = parse_texture_path[parse_texture_path.length-1];
//            String tmp_texture = "/resources/webgl/tmp/model/"+texture_name;
//            File textureFile = new File(tmp_texture);
//            boolean result = upYun.readFile(weaponDomain.getWeapon_texture(),textureFile);
//            if (result){
//                model.addAttribute("texture_path",tmp_texture);
//            }
//        }
        model.addAttribute("model_path","http://ccssz.b0.upaiyun.com/szmilitary/upload/model/mod143634374852879.obj");
        model.addAttribute("texture_path","/resources/webgl/tmp/texture/airforce1_diffuse_01.png");
        return "/webgl/webgl";
    }
}
