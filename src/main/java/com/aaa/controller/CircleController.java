package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("circle")
public class CircleController {
    @Autowired
    CircleService cs;
    @Autowired
    User_infoService us;
    @Autowired
    ReviewService rs;
    @Autowired
    ClableService cls;
    @Autowired
    CircletypeService cts;
    @RequestMapping("queryLimit")
    @ResponseBody
    public Map<String,Object> query(Integer page, Integer limit){
        return cs.query(page,limit);
    }
    @RequestMapping("query")
    @ResponseBody
    public List<Circle> queryAll(){
        System.out.println(cs.queryAll().size());
        System.out.println(cs.queryAll().get(0));
        return cs.queryAll();
    }
    @RequestMapping("circle")
    public String testss(Model model){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        /*这个是圈子的信息*/
        List<Circle> lc = cs.diffTime();
        //System.out.println(lc);
        //System.out.println(lc.get(0).getUserid());
        for (int i = 0; i < lc.size() ; i++) {
            Map<String,Object> circle = new HashMap<String,Object>();
            /*这个是获得用户的一些信息*/
            List<User_info> lus = us.queryByUserId(lc.get(i).getUserid());
            //System.out.println(lus);
            /*这个是获取评论信息的*/
            List<Review> lr = rs.queryByComposeid(lc.get(i).getCircleid());
            //System.out.println(lr);
            List<Clable> lcs = cls.queryByClableid(lc.get(i).getClableid());
            circle.put("username",lus.get(0).getUsername());
            circle.put("head",lus.get(0).getHead());
            circle.put("title",lc.get(i).getTitle());
            circle.put("content",lc.get(i).getContent());
            circle.put("clablename",lcs.get(0).getClablename());
            if(lr.size()==0){
                circle.put("rcount", 0);
            }else{
                List<User_info> lui = us.queryByUserId(lr.get(0).getUserid());
                circle.put("rname",lui.get(0).getUsername());
                circle.put("rtime",lr.get(0).getTime());
                circle.put("rcount",lr.size());
            }
            resultList.add(circle);
        }
        /*System.out.println(resultList.toString());*/
        model.addAttribute("circ",resultList);
        System.out.println(resultList.toString());
        return "cir";
    }


   /* 跳转页面*/
    @RequestMapping("find")
    public String find(Model model){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> pci = new ArrayList<Map<String, Object>>();
        /*圈子类型*/
        List<Circletype> lcts = cts.queryAll();
        System.out.println("圈子类型"+lcts.toString());
        for (int i = 0; i < lcts.size(); i++) {
            /*圈子标签*/
            List<Clable> lc = cls.queryByTypeid(lcts.get(i).getTid());
            System.out.println("圈子标签"+lc.toString());
            for (int j = 0; j <lc.size() ; j++) {
                Map<String,Object> clable = new HashMap<String,Object>();
                List<Circle> lci = cs.queryByClableid(lc.get(j).getClableid());
                System.out.println("clid"+lc.get(j).getClableid());
                System.out.println(cs.querycountByClableid(lc.get(j).getClableid()).toString());
                System.out.println("圈子中的帖子"+lci.toString());
                clable.put("clablename", lc.get(j).getClablename());
                clable.put("cpic",lc.get(j).getCpic());
                clable.put("count",cs.querycountByClableid(lc.get(j).getClableid()));
                clable.put("tname",lcts.get(i).getTname());
                System.out.println("map"+clable.toString());
                pci.add(clable);
            }
            Map<String,Object> tname = new HashMap<String,Object>();
            tname.put("tname",lcts.get(i).getTname());
            System.out.println("圈子分类"+tname.toString());
            resultList.add(tname);
        }
        System.out.println("list中的值"+resultList);
        System.out.println("pci的list中的值"+pci);
        model.addAttribute("clable",resultList);
        model.addAttribute("pci",pci);
        return "find";
    }
    @RequestMapping("general")
    public String general(){
        return "general";
    }
    /*@RequestMapping(value = "q/{qid}")
    public ModelAndView queryByQuestionid(ModelAndView mv ,@PathVariable Integer qid){
        List<Question> list = qs.queryByQuestionid(qid);
        mv.addObject("qid",list);
        mv.setViewName("questions/q");
        return mv;
    }*/
    /*<a th:href="@{'/q/'+${l.questionid}}" th:text="${l.title}"></a>*/
    @RequestMapping(value = "q/{clablename}")
    public String queryByQuestionid(Model mv ,@PathVariable String clablename){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Clable> lc = cls.queryByClablename(clablename);
        Integer clableid = lc.get(0).getClableid();
        String synopsis = lc.get(0).getSynopsis();
        Date foundtime = lc.get(0).getFoundtime();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("clableid",lc.get(0).getClableid());
        /*Question list = qs.queryByQuestionid(qid).get(0);
        mv.addAttribute("li",list);
        return "questions/q";*/
        /*8.5 3.5*/
        return "general";
    }
}