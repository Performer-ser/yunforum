package com.aaa.controller;

import com.aaa.entity.Userinfo;
import com.aaa.service.UserinfoService;
import com.aaa.util.JuheDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserinfoController {
    @Autowired
    UserinfoService us;

    @RequestMapping("login")
    @ResponseBody
    public int login(Userinfo user, HttpServletRequest request){
       HttpSession session = request.getSession();
       List<Userinfo> list=us.login(user);
       if(list.size()==1){
           session.setAttribute("LoginUser",list);
           return 1;
       }else {
           return 0;
       }

    }
    @RequestMapping("register")
    @ResponseBody
    public Integer register(Userinfo u){
        Integer a =us.register(u);
        System.out.println(a);
        return a;
    }
    /**
     * 发送注册验证码
     */
    @RequestMapping("yzm")
    @ResponseBody
    public String yzm(String phone)
    {
        String times=new Date().getTime()+"";
        times=times.substring(8,12);
        JuheDemo.getRequest2(phone,times);
        System.out.println(times);
        return times;
        /*String times = new Date().getTime()+"";
        times=times.substring(8, 12);
        JuheDemo.getRequest2(phone, times);
        System.out.println(times);
        return times;*/
    }
}
