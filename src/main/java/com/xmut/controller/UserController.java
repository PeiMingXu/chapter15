package com.xmut.controller;

import com.xmut.pojo.User;
import com.xmut.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date: 2023/4/25
 **/
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/Edglog")
    public String Edglog(){
        return "main";
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        //调用service
        User dbUser = userService.login(user);
        System.out.println("dbUser=="+dbUser);
        if (dbUser==null){
            //登陆失败
            request.setAttribute("msg","用户名或者密码错误");
            return "login";
        }else {
            //登陆成功
            request.getSession().setAttribute("USER_SESSION",dbUser);//将对象存入session
            return "redirect:/admin/main.jsp";//重定向
        }

    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();//销毁session
        return "login";//返回登陆界面
    }

}
