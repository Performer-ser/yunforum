package com.aaa.controller;

import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import com.aaa.service.LatypeService;
import com.aaa.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("latype")
public class LatypeController {
    @Autowired
    LatypeService lts;
    @RequestMapping("query")
    public String query(Model model){
        List<Latype> ll=lts.query();
        model.addAttribute("ll",ll);
        return "lable";
    }

    @RequestMapping("tags")
    public String tags(Model m){
        List<Latype> list=lts.querylatype();
        List<Lable> list1=lts.querylable();
        m.addAttribute("list",list);
        m.addAttribute("list1",list1);
        System.out.println("list="+list);
        System.out.println("list1="+list1);
        return "tags";
    }
    @RequestMapping("querys")
    @ResponseBody
    public String querys(HttpServletResponse resp){
        List<Latype> lb=lts.query();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",0);
        map.put("data",lb);
        String json= JsonMapper.mapToJson(map);
        return json;
    }
    @RequestMapping("add")
    @ResponseBody
    public int add(Latype latype){
        return lts.add(latype);
    }
    @RequestMapping("update")
    @ResponseBody
    public int update(Latype latype){
        return lts.update(latype);
    }
    @RequestMapping("delete")
    @ResponseBody
    public int delete(Integer latypeid){
        return lts.delete(latypeid);
    }

}