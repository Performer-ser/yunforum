package com.aaa.controller;

import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import com.aaa.service.LatypeService;
import com.aaa.service.SpecialService;
import com.aaa.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    SpecialService ss;


    @RequestMapping("query")
    public String query(Model model){
        List<Latype> ll=lts.query();
        model.addAttribute("ll",ll);
        return "lable";
    }

    @RequestMapping("tags")
    public String tags(Model m){
        List<Latype> list2=lts.querylatype();
        List<Lable> list1=lts.querylable();
        m.addAttribute("list2",list2);
        m.addAttribute("list1",list1);
        System.out.println("list2="+list2);
        System.out.println("list1="+list1);
        return "tags";
    }


    /**
     * 根据标签id查询所对的内容
     * @param m
     * @param lableid
     * @return
     */
    @RequestMapping(value = "t/{lableid}")
    public String queryBysid(Model m,@PathVariable Integer lableid,Integer latypeid){
        List<Map<String,Object>> list=lts.queryBysid(lableid);
        m.addAttribute("listss",list);
        System.out.println(list);
        List<Lable> listlables=lts.querylables();
        m.addAttribute("listlables",listlables);
        System.out.println(listlables);
        List<Map<String,Object>> queryxg=lts.queryxg((Integer)  list.get(0).get("latypeid"));
        m.addAttribute("queryxg",queryxg);
        System.out.println(queryxg);
        return "t";
    }
    /*@RequestMapping("adds")
    public int addattentiongz(int attentiongzid,int userid,int lableid){
        return lts.addattentiongz(attentiongzid,userid,lableid);
    }*/


    @RequestMapping(value = "a/{specialid}")
    public String queryByid(Model m ,@PathVariable Integer specialid){
        Map<String,Object> list=ss.queryByid(specialid).get(0);
        m.addAttribute("list",list);
        List<Map<String,Object>> querya= ss.querya();
        m.addAttribute("querya",querya);
        System.out.println(querya);
        System.out.println(list);
        return "a";
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
