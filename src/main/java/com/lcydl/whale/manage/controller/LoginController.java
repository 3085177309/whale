package com.lcydl.whale.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    /**
     * 进入登录页
     * @return
     */
    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }

    /**
     * 登录校验
     * @return
     */
    @PostMapping("/login")
    public String postLogin(){
        return "";
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public String logout(){
        return "/login";
    }

}
