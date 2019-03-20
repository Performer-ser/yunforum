package com.aaa.controller;


import com.aaa.entity.Latype;
import com.aaa.service.LatypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class TestController {
    @RequestMapping(value="{name}")
    public String tiaozhuan(@PathVariable String name){
        return  name;
    }

    @RequestMapping("hello")
    public String hello(){
        System.out.println("访问index.html页面");
        System.out.println("贼帅 修改");
        return "index";
    }

}