package com.aaa.controller;


import com.aaa.entity.Lable;
import com.aaa.entity.Slable;
import com.aaa.entity.Userinfo;
import com.aaa.service.MessageService;
import com.aaa.service.SlableService;
import com.aaa.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("specials")
public class SpecialController {
    @Autowired
    SpecialService ss;
    /*@Autowired
    SlableService sls;*/
    @Autowired
    MessageService ms;

    //推荐的标题
    @RequestMapping("query")
    public String query(Model m,Integer lableid,HttpServletRequest request){
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
        HttpSession session = request.getSession();
        List<Userinfo> lists=(List<Userinfo>)session.getAttribute("LoginUser");
        m.addAttribute("userlist",lists);
        System.out.println("---------------------------------------------*-*-*----------------------------");
        return "indexss";
    }
    @RequestMapping("querydz")
    public String querydz(Model m){
        List<Map<String,Object>> list= ss.querydz();
        m.addAttribute("list",list);
        System.out.println(list);
        return "hottest";
    }

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

    /**专栏
     * 推荐文章
     * 顺序查询所有文章，分页
     * @param m
     * @param pageNum
     * @return
     */
   @RequestMapping("blogs")
   public String aaa(Model m,Integer pageNum){
       if(null == pageNum){
           pageNum = 1;
       }
       List<Map<String,Object>> list=ss.queryblogs(pageNum);
       m.addAttribute("list",list);
       System.out.println(list);
       m.addAttribute("pageNum",pageNum);
       return "blogs/blogs";
   }

    /**专栏
     * 热门文章
     * 根据点赞数量进行查询文章，分页
     * @param m
     * @param pageNum
     * @return
     */
   @RequestMapping("hottests")
    public String bbb(Model m,Integer pageNum){
       if (null == pageNum){
           pageNum=1;
       }
       List<Map<String,Object>> list= ss.queryhottests(pageNum);
       m.addAttribute("list",list);
       m.addAttribute("pageNum",pageNum);
       return "blogs" +
               "/hottests";
   }
   @RequestMapping("newest")
    public String ccc(Model m,Integer pageNum){
       if (null== pageNum){
           pageNum=1;
       }
       List<Map<String,Object>> list=ss.querynewest(pageNum);
       m.addAttribute("list",list);
       m.addAttribute("pageNum",pageNum);
       return "blogs/newest";
   }
    @RequestMapping("attentionspe")
    @ResponseBody
    public int attentionspe(Integer perspid,HttpSession session){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        int result=0;
        if(ms.queryAllSpe(perspid,login.get(0).getUserid()).size()==0){
            result=ms.addSpe(perspid,login.get(0).getUserid());
        }else{
            ms.delSpe(perspid,login.get(0).getUserid());
            result=0;
        }
        return result;
    }

}
