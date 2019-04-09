package com.aaa.controller;


import com.aaa.entity.*;
import com.aaa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @Autowired
    MessageService ms;
    @Autowired
    User_infoService us;
    @Autowired
    ReplyService rps;

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
    public String quesById(Model model,@PathVariable Integer qid,HttpSession session){
        List<Map<String, Object>> list = qs.quesById(qid);
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        List<Map<String, Object>> review = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> replie = new ArrayList<Map<String, Object>>();
        List<Review> reviews = rs.queryByComposeid(qid,1);
        if(reviews.size() == 0){
            /*说明没有评论*/
        }else{
            /* System.out.println("显示评论数据"+reviews.toString());*/
            for (int i = 0; i < reviews.size(); i++) {
                Map<String, Object> mr = new HashMap<String, Object>();
                List<Userinfo> lu = us.queryByUserId(reviews.get(i).getUserid(),null);
                List<Reply> replies = rps.queryByReviewid(reviews.get(i).getReviewid());
                if(replies.size() != 0){
                    for (int j = 0; j < replies.size(); j++) {
                        Map<String, Object> ms = new HashMap<String, Object>();
                        Integer query = as.queryCount(replies.get(j).getReplyid(), 4);
                        ms.put("likenumByReply",query);
                        ms.put("replyid",replies.get(j).getReplyid());
                        Integer queryone = as.queryone(replies.get(j).getReplyid(), 4, userid);
                        ms.put("queryone",queryone);
                        ms.put("reviewid",replies.get(j).getReviewid());
                        ms.put("content",replies.get(j).getContent());
                        ms.put("from_userid",replies.get(j).getFrom_userid());
                        List<Userinfo> lu1 = us.queryByUserId(replies.get(j).getFrom_userid(),null);
                        List<Userinfo> lu2 = us.queryByUserId(replies.get(j).getTo_userid(),null);
                        /* System.out.println("用户"+lu2);*/
                        ms.put("from_username",lu1.get(0).getUsername());
                        ms.put("to_username",lu2.get(0).getUsername());
                        ms.put("recoverytime",replies.get(j).getRecoverytime());
                        replie.add(ms);
                    }
                    System.out.println("回复表中的内容"+replie.toString());
                    System.out.println("回复表中的数量"+replie.size());
                }
                /* System.out.println("回复表根据id"+replies.toString());*/
                mr.put("reviewid",reviews.get(i).getReviewid());
                mr.put("likenum",as.queryCount(reviews.get(i).getReviewid(), 1));
                mr.put("username",lu.get(0).getUsername());
                mr.put("userid",lu.get(0).getUserid());
                mr.put("head",lu.get(0).getHead());
                Integer queryone = as.queryone(reviews.get(i).getReviewid(), 1, userid);
                mr.put("queryone",queryone);
                mr.put("count",reviews.get(i).getContent());
                mr.put("time",reviews.get(i).getTime());
                mr.put("num",reviews.size());
                review.add(mr);
            }
        }
        System.out.println("评论表中的数据"+review.toString());
        System.out.println("评论表中的数量"+review.size());
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
            Integer atten = qs.byAttentionQuestion(ids);//查询问答关注数
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
            q.put("atten",atten);
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
        model.addAttribute("review",review);
        model.addAttribute("replie",replie);
        model.addAttribute("count",review.size()+replie.size());
        return "questions/q";
    }
    @RequestMapping("queryLatype")
    public String queryLatype(Model model,HttpSession session){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        if(login==null){
            return "redirect:../logins";
        }else{
            List<Latype> list = qs.queryLatype();

            List<Lable> lists = qs.queryLableByLatypeid();
            model.addAttribute("lists",lists);
            System.out.println("lists"+lists);

            model.addAttribute("list",list);
            System.out.println("list"+list);
            return "questions/ask";
        }

    }

    @RequestMapping(value = "addByReview")
    public String addByReview(Model mv, String text, HttpSession session, Integer qid){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        if(userid == 0)return "logins";
        Review r = new Review();
        r.setContent(text);
        r.setComposeid(qid);
        /*从session中获取获取用户的id*/
        r.setUserid(userid);
        qs.addByReview(r);
        return "redirect:/questions/q/"+qid;
    }
    @RequestMapping("addByReply")
    public String addByReply(Model mv, String text, HttpSession session,Integer reviewid,Integer touserid,Integer qid){
        /*System.out.println("内容"+text);
        System.out.println("内容"+reviewid);
        System.out.println("内容"+touserid);
        System.out.println("内容"+circleid);*/
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        if(userid == 0)return "logins";
        Reply r = new Reply();
        r.setContent(text);
        /*从session中获取对象*/
        r.setFrom_userid(userid);
        r.setTo_userid(touserid);
        r.setReviewid(reviewid);
        rps.addByReply(r);
        return "redirect:/questions/q/"+qid;
    }
    @RequestMapping("add2")
    @ResponseBody
    public Integer add2(HttpSession session,Question que, String lablename){

        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        que.setLableid(qs.querylablename(lablename).get(0).getLableid().toString());
        System.out.println("lableName = " + lablename);
        /*spe.setLableid(5)*/;
        //spe.setUserid(1);
        que.setUserid(login.get(0).getUserid());
        qs.addspe(que);
        return 1;
    }



    //添加浏览数据
    @RequestMapping(value ="addBrowse/{id}")
    public String addBrowse(HttpSession session,Browse browse,@PathVariable Integer id){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        if(login==null){
            return "redirect:/questions/q/"+id;
        }else{
            browse.setUserid(login.get(0).getUserid().toString());
            browse.setComposeid(id);
            Integer is = qs.addBrowse(browse);
            /*"@{'/questions/q/'+${l.questionid}}"*/
            System.out.println("is"+is);
            System.out.println("id"+id);
            return "redirect:/questions/q/"+id;
        }
    }
    //查看标签详情根据标签名称得到标签id传到详情页面
    @RequestMapping(value = "queryLableByLablename/{lablename}")
    public String queryLableByLablename(@PathVariable String lablename){
        int its = qs.queryLableByLablename(lablename);
        System.out.println(its+"its");
        return "redirect:/latype/t/"+its;
    }
    @RequestMapping("attentionque")
    @ResponseBody
    public int attentionque(Integer questionid, HttpSession session){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        int result=0;
        if(ms.queryAllQue(questionid,login.get(0).getUserid()).size()==0){
            result=ms.addQue(questionid,login.get(0).getUserid());
        }else{
            ms.delQue(questionid,login.get(0).getUserid());
            result=0;
        }
        return result;
    }



}


