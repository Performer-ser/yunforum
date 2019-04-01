package com.aaa.controller;


import com.aaa.entity.Lable;
import com.aaa.entity.Question;
import com.aaa.service.AdmireService;
import com.aaa.service.QuestionService;
import com.aaa.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        /*for (Map<String, Object> q:list){
            //System.out.println(q.get("qlablename"));
            List<String> ls = new ArrayList<String>();
            String s = q.get("lablename").toString();
            Integer ids = Integer.parseInt(q.get("questionid").toString());
            System.out.println("ids:"+ids);
            Integer nums = qs.byAdmire(ids);// 点赞数
            Integer bro = qs.byBrowse(ids);//浏览数量
            Integer rev = qs.byReview(ids);//回答数量
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String ss = new SimpleDateFormat("yyyy-MM-dd").format(q.get("time")).toString();
            try {
                Date times = sdf.parse(q.get("time").toString());
                Date d = new Date();
                long nd = 1000 * 24 * 60 * 60;
                long nh = 1000 * 60 * 60;
                long nm = 1000 * 60;
                long ns = 1000;
                // 获得两个时间的毫秒时间差异
                long diff =  d.getTime()- times.getTime();
                // 计算差多少天
                long day = diff / nd;
                System.out.println("day"+day);
                // 计算差多少小时
                long hour = diff % nd / nh;
                System.out.println("hour"+hour);
                // 计算差多少分钟
                long min = diff % nd % nh / nm;
                System.out.println(min+"min");
                // 计算差多少秒//输出结果
                long sec = diff % nd % nh % nm / ns;
                System.out.println("sec"+sec);
                if(day>30){
                    q.put("details",ss);
                }else if(day<=30&&day>=1){
                    q.put("details",day+"天前");
                }else if(day<1&&hour>=1){
                    q.put("details",hour+"小时前");
                }else if(day<1&&hour<1&&min>=1){
                    q.put("details",min+"分钟前");
                }else if(day<1&&hour<1&&min<1){
                    q.put("details","刚刚");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        }*/
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
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String ss = new SimpleDateFormat("yyyy-MM-dd").format(q.get("time")).toString();
            try {
                Date times = sdf.parse(q.get("time").toString());
                Date d = new Date();
                long nd = 1000 * 24 * 60 * 60;
                long nh = 1000 * 60 * 60;
                long nm = 1000 * 60;
                long ns = 1000;
                // 获得两个时间的毫秒时间差异
                long diff =  d.getTime()- times.getTime();
                // 计算差多少天
                long day = diff / nd;
                System.out.println("day"+day);
                // 计算差多少小时
                long hour = diff % nd / nh;
                System.out.println("hour"+hour);
                // 计算差多少分钟
                long min = diff % nd % nh / nm;
                System.out.println(min+"min");
                // 计算差多少秒//输出结果
                long sec = diff % nd % nh % nm / ns;
                System.out.println("sec"+sec);
                if(day>30){
                    q.put("details",ss);
                }else if(day<=30&&day>=1){
                    q.put("details",day+"天前");
                }else if(day<1&&hour>=1){
                    q.put("details",hour+"小时前");
                }else if(day<1&&hour<1&&min>=1){
                    q.put("details",min+"分钟前");
                }else if(day<1&&hour<1&&min<1){
                    q.put("details","刚刚");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        uuuu(1,model);
        List<Map<String,Object>> lm=rs.queryOneAll(1,(page-1)*20);
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        for (int i=0;i<lm.size();i++){
            Map<String,Object> m=qs.queryTitle(Integer.valueOf(lm.get(i).get("composeid").toString()));
            List<Map<String,Object>> ll=as.query(4,Integer.valueOf(lm.get(i).get("reviewid").toString()),1);
            lm.get(i).put("title",m.get("title"));
            lm.get(i).put("caina",m.get("reviewid"));
            lm.get(i).put("admire",ll.get(0).get("count"));
            Map<String,Object> q=new HashMap<>();
        }
        Double wq=Double.valueOf(rs.queryUserAll(1).size());
        Double c=Math.ceil(wq/20);
        for (int i=0;i<c;i++){
            Map<String,Object> m=new HashMap<>();
            m.put("ass",i+1);
            m.put("page",page);
            aw.add(m);
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
    @RequestMapping("qqq")
    public String qqq(Integer page,Model model){
        List<Map<String,Object>> lq=qs.queryUser(1,(page-1)*20);
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        for (int i=0; i<lq.size();i++){
            List<Map<String,Object>> ll=as.query(1,Integer.valueOf(lq.get(i).get("questionid").toString()),1);
            lq.get(i).put("admire",ll.get(0).get("count"));
        }
        Double wq=Double.valueOf(qs.queryUserAll(1).size());
        Double c=Math.ceil(wq/20);
        for (int i=0;i<c;i++){
            Map<String,Object> m=new HashMap<>();
            m.put("ass",i+1);
            m.put("page",page);
            aw.add(m);
        }
        System.out.printf(lq.toString());
        model.addAttribute("lq",lq);
        model.addAttribute("a",aw);
        return "personQues";
    }
    public void uuuu(Integer userid,Model model){
        model.addAttribute("user",1111);
    }
    @RequestMapping("queryLatype")
    public void queryLatype(Model model){
        List<Lable> list = qs.queryLatype();
        model.addAttribute("list",list);
    }

}
