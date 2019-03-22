/*
package com.aaa.controller;

import com.aaa.entity.Userinfo;
import com.aaa.service.UserinfoService;
import com.aaa.util.JsonMapper;
import com.aaa.util.JuheDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users")
public class UserinfoController {
    @Autowired
    UserinfoService us;
    @RequestMapping("login")
    @ResponseBody
    public int login(Userinfo user){
       List<Userinfo> list=us.login(user);
       if(list.size()==1){
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
    */
/**
     * 发送注册验证码
     *//*

    @RequestMapping("yzm")
    @ResponseBody
    public String yzm(String phone)
    {
        String times=new Date().getTime()+"";
        times=times.substring(8,12);
        JuheDemo.getRequest2(phone,times);
        System.out.println(times);
        return times;
        */
/*String times = new Date().getTime()+"";
        times=times.substring(8, 12);
        JuheDemo.getRequest2(phone, times);
        System.out.println(times);
        return times;*//*

    }
    @RequestMapping("queryPage")
    @ResponseBody
    public String queryPage(Integer page,Integer limit){
        List<Userinfo> lu=us.queryPage((page-1)*limit,limit);
        List<Userinfo> lua=us.queryAll();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",lua.size());
        map.put("data",lu);
        String json= JsonMapper.mapToJson(map);
        return json;
    }
    @RequestMapping("disabled")
    @ResponseBody
    public int disabled(Integer userid,Integer status){
        int a=0;
        if(status==1){
            a=us.disabled(userid,0);
        }
        else{
            a=us.disabled(userid,1);
        }
        return a;
    }
    @RequestMapping("queryOne")
    public String queryOne(Integer userid, Model model){
        model.addAttribute("lu",us.queryOne(userid));
        return "information";
    }
}
*/
