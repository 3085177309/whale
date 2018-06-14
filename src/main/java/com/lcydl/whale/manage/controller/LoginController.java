package com.lcydl.whale.manage.controller;

import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.util.LoginUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public R postLogin(String username, String password){
        //password明文密码处理
        password = LoginUtil.encrypt(username,password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return R.error("用户或密码错误");
        }
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

}
