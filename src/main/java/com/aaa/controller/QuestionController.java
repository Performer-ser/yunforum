package com.aaa.controller;


import com.aaa.entity.Question;
import com.aaa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService qs;

    Question ques = new Question();
    @RequestMapping("questions")
    public String queryQuestionsByQlable(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        /*for (Map<String, Object> q:list){
            //System.out.println(q.get("qlablename"));
            List<String> ls = new ArrayList<String>();
            String s = q.get("lablename").toString();
            Integer ids = Integer.parseInt(q.get("questionid").toString());
            System.out.println("ids:"+ids);
            Integer nums = qs.byAdmire(ids);// 点赞数
            Integer bro = qs.byBrowse(ids);//浏览数量
            Integer rev = qs.byReview(ids);//回答数量

            System.out.println("nums:"+nums);
            q.put("nums",nums);
            q.put("bro",bro);
            q.put("rev",rev);
            String[] str = s.split(",");
            for(int i = 0;i<str.length;i++){
                ls.add(str[i]);
            }
            q.put("lablenames",ls);
            System.out.println(q.get("lablenames"));
        }
        System.out.println(list);*/
        model.addAttribute("list",list);
        return "questions/questions";
    }
    @RequestMapping("subscribed")
    public String b(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        System.out.println("就去了为我推送页面");
        return "questions/subscribed";
    }
    @RequestMapping("unanswered")
    public String c(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        System.out.println("就去了等待回答页面");
        return "questions/unanswered";
    }
    @RequestMapping("hottest")
    public String d(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        System.out.println("就去了热门回答页面");
        return "questions/hottest";
    }
    @RequestMapping(value="q/{qid}")
    public String quesById(Model model,@PathVariable Integer qid){
        List<Map<String, Object>> list = qs.quesById(qid);
        /*Map<String, Object> lists = qs.quesById(qid).get(0);*/
        for (Map<String, Object> q:list){
            //System.out.println(q.get("qlablename"));
            List<String> ls = new ArrayList<String>();
            String s = q.get("lablename").toString();
            Integer ids = Integer.parseInt(q.get("questionid").toString());
            System.out.println("ids:"+ids);
            Integer nums = qs.byAdmire(ids);// 点赞数
            Integer bro = qs.byBrowse(ids);//浏览数量
            Integer rev = qs.byReview(ids);//回答数量

            System.out.println("nums:"+nums);
            q.put("nums",nums);
            q.put("bro",bro);
            q.put("rev",rev);
            String[] str = s.split(",");
            for(int i = 0;i<str.length;i++){
                ls.add(str[i]);
            }
            q.put("lablenames",ls);
            System.out.println(q.get("lablenames"));
        }
        model.addAttribute("list",list);
        System.out.println("list:---"+list);
        return "questions/q";
    }


}
