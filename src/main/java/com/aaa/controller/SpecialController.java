package com.aaa.controller;


import com.aaa.entity.Lable;
import com.aaa.entity.Slable;
import com.aaa.service.SlableService;
import com.aaa.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
/*@RequestMapping("special")*/
public class SpecialController {
    @Autowired
    SpecialService ss;
    /*@Autowired
    SlableService sls;*/

    //推荐的标题
    @RequestMapping("query")
    public String query(Model m,Integer lableid){
        System.out.println(lableid);
        List<Map<String,Object>> list=ss.query(lableid);
        List<Map<String,Object>> list2=ss.querycenter();
        List<Map<String,Object>> listxin=ss.query1();
        m.addAttribute("listxin",listxin);
        m.addAttribute("list2",list2);
        List<Lable> list1=ss.querySlable();
        System.out.println(list1);
        m.addAttribute("list1",list1);
        //System.out.println(list1.toString());
        m.addAttribute("list",list);
        if(lableid!=null){
            m.addAttribute("list2",null);
        }
        if(lableid!=null){
            m.addAttribute("listxin",null);
        }
        System.out.println("---------------------------------------------*-*-*----------------------------");
        return "indexss";
    }

   /* *//**
     * 最新内容
     * @param m
     * @return
     *//*
    @RequestMapping("xin")
    public String query1(Model m){
        List<Map<String,Object>> list=ss.query1();
        List<Slable> list1=ss.querySlable();
        System.out.println(list1);
        m.addAttribute("list1",list1);
        //System.out.println(list1.toString());
        m.addAttribute("list",list);
        System.out.println(list.toString());
        System.out.println("---------------------------------------------*-*-*----------------------------");
        return "xin";
    }*/
    @RequestMapping(value = "a/{specialid}")
    public String queryByid(Model m ,@PathVariable Integer specialid){

        Map<String,Object> list=ss.queryByid(specialid).get(0);
        m.addAttribute("list",list);
        return "a";
    }

   /*@RequestMapping(value = "query2")
    public String query2(Model m, Integer slableid){
        Map<String,Object> map=ss.query2(slableid);
        System.out.println(map);
        m.addAttribute("map",map);

        List<Slable> list1=sls.querySlable();
        System.out.println(list1);
        m.addAttribute("list1",list1);
        m.addAttribute("list",list);
        Map<String,Object> list2=sls.query2(slableid);
        System.out.println(list2);
        m.addAttribute("list2",list2);


        return "indexs";
    }*/
}
