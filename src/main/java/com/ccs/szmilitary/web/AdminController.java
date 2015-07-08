package com.ccs.szmilitary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhang on 2015/7/2.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin")
    public String admin(Model model){
        setPath(model,"index","index");
        return "/admin/index";
    }

    private void setPath(Model model,String module,String action){
        model.addAttribute("module",module);
        model.addAttribute("action",action);
    }
}
