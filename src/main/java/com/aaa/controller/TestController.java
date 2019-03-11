package com.aaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("hello")
    public String hello(){
        System.out.println("访问index.html页面");
        return "index";
    }

}