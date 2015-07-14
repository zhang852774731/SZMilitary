package com.ccs.szmilitary.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/login")
    public String admin_login(HttpServletRequest req, Model model){
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = null;

        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "对不起，没有该账户信息";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名或者密码错误";
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
            error = "你输入的账户密码错误超过5次，现在暂时关闭，请您联系客服";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = "您的账户已经被关闭，如有疑问，请联系客服";
        } else if ("Captcha.error".equals(exceptionClassName)) {
            error = "验证码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "/admin/login";
    }

    private void setPath(Model model,String module,String action){
        model.addAttribute("module",module);
        model.addAttribute("action",action);
    }
}
