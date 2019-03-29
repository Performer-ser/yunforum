package com.aaa.controller;


import com.aaa.entity.Question;
import com.aaa.service.AdmireService;
import com.aaa.service.QuestionService;
import com.aaa.service.ReviewService;
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
    @Autowired
    ReviewService rs;
    @Autowired
    AdmireService as;

    Question ques = new Question();
    @RequestMapping("questions")
    public String queryQuestionsByQlable(Model model,Integer pageNum){
        if(null == pageNum){
            pageNum = 1;
        }
        List<Map<String, Object>> list = qs.queryQuestionsByQlable(pageNum);
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
        System.out.println(list);
        System.out.println("pageNum"+pageNum);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("list",list);
        return "questions/questions";
    }
    @RequestMapping("subscribed")
    public String b(Model model,Integer pageNum){
        if(null == pageNum){
            pageNum = 1;
        }
        List<Map<String, Object>> list = qs.queryQuestionsByQlable(pageNum);
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
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("list",list);
        System.out.println("就去了为我推送页面");
        return "questions/subscribed";
    }
    @RequestMapping("unanswered")
    public String c(Model model,Integer pageNum){
        if(null == pageNum){
            pageNum = 1;
        }
        List<Map<String, Object>> list = qs.queryQuestionsByQlable(pageNum);
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
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("list",list);
        List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> m:list){
            Integer i = (Integer) m.get("rev");
            System.out.println("i"+i);
        }
        /**/
        System.out.println("就去了等待回答页面");
        return "questions/unanswered";
    }
    @RequestMapping("hottest")
    public String d(Model model,Integer pageNum){
        if(null == pageNum){
            pageNum = 1;
        }
        List<Map<String, Object>> list = qs.queryQuestionsByQlable(pageNum);
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
        model.addAttribute("pageNum",pageNum);
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
        List<Map<String, Object>> lists = qs.queryReviewByQuestionid(qid);
        model.addAttribute("lists",lists);

        model.addAttribute("list",list);
        System.out.println("list:---"+list);
        System.out.println("lists:---"+lists);
        return "questions/q";
    }
    @RequestMapping("answers")
    public String answers(Integer page,Model model){
        List<Map<String,Object>> lm=rs.queryOneAll(1);
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        for (int i=0;i<lm.size();i++){
            Map<String,Object> m=qs.queryTitle(Integer.valueOf(lm.get(i).get("composeid").toString()));
            List<Map<String,Object>> ll=as.query(4,Integer.valueOf(lm.get(i).get("composeid").toString()),1);
            lm.get(i).put("title",m.get("title"));
            lm.get(i).put("admire",ll.get(0).get("count"));
            Map<String,Object> q=new HashMap<>();
            if((i+1)%20==0){
                q.put("ass",(i+1)/20);
                aw.add(q);
            }else{
                q.put("ass",1);
                aw.add(q);
            }
        }
        System.out.printf(lm.toString());
        model.addAttribute("lm",lm);
        model.addAttribute("a",aw);
        return "personquestion";
    }
    @RequestMapping("ask")
    public String ask(){
        System.out.println("进入了ask页面");
        return "questions/ask";
    }

}
