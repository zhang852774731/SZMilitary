package com.ccs.szmilitary.web;

import com.ccs.szmilitary.domain.WeaponDomain;
import com.ccs.szmilitary.service.WeaponService;
import com.ccs.szmilitary.util.Setting;
import main.java.com.UpYun;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * Created by zhang on 2015/7/2.
 */
@Controller
public class AdminWeapon {
    @Autowired
    private WeaponService weaponService;
    @Autowired
    private UpYun upYun;
    @Autowired
    private Setting setting;
    @RequestMapping(value = "/admin/weapon/add")
    public String weaponAdd(HttpServletRequest request,Model model){
        setPath(model,"weapon","add");
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
        return "admin/weapon/add";
    }

    @RequestMapping(value = "/admin/weapon/list")
    public String weaponList(Model model){
        List<WeaponDomain> weaponDomains =  weaponService.getAll();
        model.addAttribute("weaponList",weaponDomains);
        setPath(model, "weapon", "list");
        return "admin/weapon/list";
    }

    @RequestMapping(value = "/admin/weapon/add/action" ,method = RequestMethod.POST)
    public String weaponAddAction(HttpServletRequest request,
                                  @RequestParam(value = "weapon_thumbnail") CommonsMultipartFile weapon_thumbnail,
                                  @RequestParam(value = "weapon_model") CommonsMultipartFile weapon_model,
                                  @RequestParam(value = "weapon_texture") CommonsMultipartFile weapon_texture){
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String weapon_thumbnail_path = "";
        String weapon_model_path = "";
        String weapon_texture_path = "";
        File filemake = new File(filePath);
        if(!filemake.exists()){
            try {
                filemake.mkdirs();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        //判断上传的文件是否为空，需要注意的是，不能使用是否等于null的这种方式，无论上传的文件是否为空，CommonsMultipartFile类型的对象都不为空
        if (!weapon_thumbnail.isEmpty()){
            Timestamp d = new Timestamp(System.currentTimeMillis());
            weapon_thumbnail_path = "pic"+d.getTime()+randInt()+"."+FilenameUtils.getExtension(weapon_thumbnail.getOriginalFilename());
            File upyunFile_thumbnail = saveFile(filePath+weapon_thumbnail_path,weapon_thumbnail);//这里主要的作用是转换CommonsMultipartFile为File，实际File存储在又拍云上
            weapon_thumbnail_path = "/szmilitary/upload/thumbnail/"+weapon_thumbnail_path;
            try {
                boolean result = upYun.writeFile(weapon_thumbnail_path, upyunFile_thumbnail,true);//上传文件到又拍云中
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (!weapon_model.isEmpty()){
            Timestamp d = new Timestamp(System.currentTimeMillis());
            weapon_model_path = "mod"+d.getTime()+randInt()+"."+FilenameUtils.getExtension(weapon_model.getOriginalFilename());
            File upyunFile_model = saveFile(filePath+weapon_model_path,weapon_model);
            weapon_model_path = "/szmilitary/upload/model/"+weapon_model_path;
            try {
                upYun.writeFile(weapon_model_path,upyunFile_model,true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (!weapon_texture.isEmpty()){
            Timestamp d = new Timestamp(System.currentTimeMillis());
            weapon_texture_path = "texture"+d.getTime()+randInt()+"."+FilenameUtils.getExtension(weapon_texture.getOriginalFilename());
            File upyunFile_texture = saveFile(filePath+weapon_texture_path,weapon_texture);
            weapon_texture_path = "/szmilitary/upload/texture/"+weapon_texture_path;
            try {
                upYun.writeFile(weapon_texture_path,upyunFile_texture);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String weapon_name = request.getParameter("weapon_name");
        String weapon_country = request.getParameter("weapon_country");
        String weapon_category = request.getParameter("weapon_category");
        String weapon_attr = request.getParameter("weapon_attr");
        WeaponDomain weaponDomain = new WeaponDomain();
        weaponDomain.setWeapon_name(weapon_name);
        weaponDomain.setWeapon_category(weapon_category);
        weaponDomain.setWeapon_country(weapon_country);
        weaponDomain.setWeapon_attr(weapon_attr);
        weaponDomain.setWeapon_thumbnail("http://ccssz.b0.upaiyun.com" + weapon_thumbnail_path);
        weaponDomain.setWeapon_model("http://ccssz.b0.upaiyun.com" + weapon_model_path);
        weaponDomain.setWeapon_texture("http://ccssz.b0.upaiyun.com"+weapon_texture_path);
        weaponService.addWeapon(weaponDomain);
        return "redirect:/admin/weapon/list";
    }

    @RequestMapping(value = "/admin/weapon/delete")
    private @ResponseBody Object weaponDel(@RequestParam(value = "weaponid") String weaponid){
        weaponService.delWeapon(Integer.parseInt(weaponid));
        return "success";
    }

    @RequestMapping(value = "/admin/weapon/edit")
    private String weaponEdit(@RequestParam(value = "weaponid") String weaponid,Model model){
        WeaponDomain weaponDomain = weaponService.getWeaponById(Integer.parseInt(weaponid));
        model.addAttribute("weapondomain",weaponDomain);
        return "/admin/weapon/edit";
    }

    @RequestMapping(value = "/admin/weapon/edit/action")
    private String weaponEditAction(HttpServletRequest request,
                                    @RequestParam(value = "weapon_thumbnail") CommonsMultipartFile weapon_thumbnail,
                                    @RequestParam(value = "weapon_model") CommonsMultipartFile weapon_model){
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String weaponId = request.getParameter("weaponid");
        WeaponDomain weaponDomain = weaponService.getWeaponById(Integer.parseInt(weaponId));
        String weapon_thumbnail_path = "";
        String weapon_model_path = "";
        File filemake = new File(filePath);
        if(!filemake.exists()){
            try {
                filemake.mkdirs();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        //判断上传的文件是否为空，需要注意的是，不能使用是否等于null的这种方式，无论上传的文件是否为空，CommonsMultipartFile类型的对象都不为空
        //如果为空的话，说明没有更新，不为空的话，说明有更新
        if (!weapon_thumbnail.isEmpty()){
            Timestamp d = new Timestamp(System.currentTimeMillis());
            weapon_thumbnail_path = "pic"+d.getTime()+randInt()+"."+FilenameUtils.getExtension(weapon_thumbnail.getOriginalFilename());
            File upyunFile_thumbnail = saveFile(filePath+weapon_thumbnail_path,weapon_thumbnail);
            weapon_thumbnail_path = "/szmilitary/upload/thumbnail/"+weapon_thumbnail_path;
            try {
                boolean result = upYun.writeFile(weapon_thumbnail_path, upyunFile_thumbnail,true);//上传文件到又拍云中
            }catch (Exception e){
                e.printStackTrace();
            }
            weaponDomain.setWeapon_thumbnail("http://ccssz.b0.upaiyun.com"+weapon_thumbnail_path);
        }
        if (!weapon_model.isEmpty()){
            Timestamp d = new Timestamp(System.currentTimeMillis());
            weapon_model_path = "mod"+d.getTime()+randInt()+"."+FilenameUtils.getExtension(weapon_model.getOriginalFilename());
            File upyunFile_model = saveFile(filePath+weapon_model_path,weapon_model);
            weapon_model_path = "/szmilitary/upload/model/"+weapon_model_path;
            try {
                boolean result = upYun.writeFile(weapon_model_path,upyunFile_model,true);
            }catch (Exception e){
                e.printStackTrace();
            }
            weaponDomain.setWeapon_model("http://ccssz.b0.upaiyun.com"+weapon_model_path);
        }
        String weapon_name = request.getParameter("weapon_name");
        String weapon_country = request.getParameter("weapon_country");
        String weapon_category = request.getParameter("weapon_category");
        String weapon_attr = request.getParameter("weapon_attr");
        weaponDomain.setWeapon_name(weapon_name);
        weaponDomain.setWeapon_category(weapon_category);
        weaponDomain.setWeapon_country(weapon_country);
        weaponDomain.setWeapon_attr(weapon_attr);
        weaponService.updateWeapon(weaponDomain);
        return "redirect:/admin/weapon/list";
    }

    private void setPath(Model model,String module,String action){
        model.addAttribute("module",module);
        model.addAttribute("action",action);
    }

    private int randInt() {
        Random rand = new Random();
        int randomNum = rand.nextInt((99 - 1) + 1) + 1;
        return randomNum;
    }

    private File saveFile(String filepath,MultipartFile file) {
        File tmpFile = new File(filepath);
        try {
            // 转存文件
            file.transferTo(tmpFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmpFile;
    }
}
