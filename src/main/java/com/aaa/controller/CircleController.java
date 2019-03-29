package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.*;
import com.aaa.util.ForFlie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CliqueService clqs;
    @Autowired
    ReplyService rps;

    @RequestMapping("queryLimit")
    @ResponseBody
    public Map<String, Object> query(Integer page, Integer limit) {
        return cs.query(page, limit);
    }

    @RequestMapping("query")
    @ResponseBody
    public List<Circle> queryAll() {
        System.out.println(cs.queryAll().size());
        System.out.println(cs.queryAll().get(0));
        return cs.queryAll();
    }

    @RequestMapping("circle")
    public String testss(Model model) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        /*这个是圈子的信息*/
        List<Circle> lc = cs.diffTime();
        //System.out.println(lc);
        //System.out.println(lc.get(0).getUserid());
        for (int i = 0; i < lc.size(); i++) {
            Map<String, Object> circle = new HashMap<String, Object>();
            /*这个是获得用户的一些信息*/
            List<User_info> lus = us.queryByUserId(lc.get(i).getUserid());
            //System.out.println(lus);
            /*这个是获取评论信息的*/
            List<Review> lr = rs.queryByComposeid(lc.get(i).getCircleid());
            //System.out.println(lr);
            List<Clable> lcs = cls.queryByClableid(lc.get(i).getClableid());
            circle.put("username", lus.get(0).getUsername());
            circle.put("userid", lus.get(0).getUserid());
            circle.put("head", lus.get(0).getHead());
            circle.put("title", lc.get(i).getTitle());
            circle.put("content", lc.get(i).getContent());
            circle.put("circleid",lc.get(i).getCircleid());
            circle.put("clablename", lcs.get(0).getClablename());
            if (lr.size() == 0) {
                circle.put("rcount", 0);
            } else {
                List<User_info> lui = us.queryByUserId(lr.get(0).getUserid());
                circle.put("rname", lui.get(0).getUsername());
                circle.put("rtime", lr.get(0).getTime());
                circle.put("rcount", lr.size());
            }
            resultList.add(circle);
        }
        /*System.out.println(resultList.toString());*/
        model.addAttribute("circ", resultList);
        System.out.println(resultList.toString());
        return "cir";
    }


    /* 跳转页面*/
    @RequestMapping("find")
    public String find(Model model) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> pci = new ArrayList<Map<String, Object>>();
        /*圈子类型*/
        List<Circletype> lcts = cts.queryAll();
        System.out.println("圈子类型" + lcts.toString());
        for (int i = 0; i < lcts.size(); i++) {
            /*圈子标签*/
            List<Clable> lc = cls.queryByTypeid(lcts.get(i).getTid());
            System.out.println("圈子标签" + lc.toString());
            for (int j = 0; j < lc.size(); j++) {
                Map<String, Object> clable = new HashMap<String, Object>();
                List<Circle> lci = cs.queryByClableid(lc.get(j).getClableid());
                System.out.println("clid" + lc.get(j).getClableid());
                System.out.println(cs.querycountByClableid(lc.get(j).getClableid()).toString());
                System.out.println("圈子中的帖子" + lci.toString());
                clable.put("clablename", lc.get(j).getClablename());
                clable.put("cpic", lc.get(j).getCpic());
                clable.put("count", cs.querycountByClableid(lc.get(j).getClableid()));
                clable.put("tname", lcts.get(i).getTname());
                System.out.println("map" + clable.toString());
                pci.add(clable);
            }
            Map<String, Object> tname = new HashMap<String, Object>();
            tname.put("tname", lcts.get(i).getTname());
            System.out.println("圈子分类" + tname.toString());
            resultList.add(tname);
        }
        System.out.println("list中的值" + resultList);
        System.out.println("pci的list中的值" + pci);
        model.addAttribute("clable", resultList);
        model.addAttribute("pci", pci);
        return "find";
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
    public String queryByQuestionid(Model mv, @PathVariable String clablename) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Clable> lc = cls.queryByClablename(clablename);/*圈子名称*/
        /* Integer clableid = lc.get(0).getClableid();*//*圈子id*//*
        String synopsis = lc.get(0).getSynopsis();*//*圈子简介*//*
        Date foundtime = lc.get(0).getFoundtime();*//*圈子创建时间*//*
        String cpic = lc.get(0).getCpic();*//*圈子图片*//*
        List<User_info> user_infos = us.queryByUserId(lc.get(0).getUserid());//用户*/
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clablename", lc.get(0).getClablename());
        map.put("synopsis", lc.get(0).getSynopsis());
        map.put("foundtime", lc.get(0).getFoundtime());
        map.put("cpic", lc.get(0).getCpic());
        map.put("user_infos", us.queryByUserId(lc.get(0).getUserid()).get(0));
        map.put("integer", clqs.queryCount(lc.get(0).getClableid(), 1));
        List<Circle> circles = cs.queryByClableid(lc.get(0).getClableid());
        for (int i = 0; i < circles.size(); i++) {
            System.out.println("圈子信息"+lc.toString());
            List<Review> lr = rs.queryByComposeid(circles.get(i).getCircleid());
            List<User_info> user_infos = us.queryByUserId(circles.get(i).getUserid());
            Map<String, Object> cir = new HashMap<String, Object>();
            cir.put("head", user_infos.get(0).getHead());
            cir.put("title", circles.get(i).getTitle());
            cir.put("userid",user_infos.get(0).getUserid());
            cir.put("username", user_infos.get(0).getUsername());
            cir.put("circleid",circles.get(i).getCircleid());
            cir.put("clablename", lc.get(0).getClablename());
            if (lr.size() == 0) {
                cir.put("rcount", 0);
            } else {
                List<User_info> lui = us.queryByUserId(lr.get(i).getUserid());
                cir.put("rname", lui.get(i).getUsername());
                cir.put("rtime", lr.get(i).getTime());
                cir.put("rcount", lr.size());
            }
            resultList.add(cir);
        }
        map.put("reviews", rs.queryByComposeid(lc.get(0).getClableid()));
        /*Integer integer = clqs.queryCount(lc.get(0).getClableid(), 0);*//*多少人关注*//*
        List<Circle> circles = cs.queryByClableid(lc.get(0).getClableid());//圈子标签名称
        List<Review> reviews = rs.queryByComposeid(lc.get(0).getClableid());//评论*/
        /*Question list = qs.queryByQuestionid(qid).get(0);
        mv.addAttribute("li",list);
        return "questions/q";*/
        /*8.5 3.5*/
        mv.addAttribute("list", map);
        System.out.println(map.toString());
        mv.addAttribute("cir", resultList);
        System.out.println(resultList.toString());
        return "general";
    }

    @RequestMapping(value = "attention/{clablenameden}")
    public String attention(Model mv, @PathVariable String clablename){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Clable> lc = cls.queryByClablename(clablename);
        List<Clique> lcq = clqs.queryByClableid(lc.get(0).getClableid());
        for (int i = 0; i < lcq.size() ; i++) {
            List<User_info> user_infos = us.queryByUserId(lcq.get(i).getUserid());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userid",user_infos.get(0).getUserid());
            map.put("username",user_infos.get(0).getUsername());
            map.put("head",user_infos.get(0).getHead());
            List<Circle> circles = cs.queryByUserid(user_infos.get(0).getUserid());
            map.put("topic",circles.size());
            List<Review> reviews = rs.queryByUserid(user_infos.get(0).getUserid());
            map.put("comments",reviews.size());
            List<Clique> cliques = clqs.queryByUserid(user_infos.get(0).getUserid());
            map.put("ctime",cliques.get(0).getCtime());
            resultList.add(map);
        }
        System.out.println(resultList.toString());
        mv.addAttribute("list",resultList);
        return "attention";
    }
    @RequestMapping(value = "personal/{userid}")
    public String personal(Model mv, @PathVariable Integer userid){
        System.out.println("访问个人页面");
        return  "personal";
    }
    @RequestMapping(value = "show/{circleid}")
    public String attention(Model mv, @PathVariable Integer circleid){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> review = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> replie = new ArrayList<Map<String, Object>>();
        List<Circle> circles = cs.queryByCircleid(circleid);
        List<Clable> clables = cls.queryByClableid(circles.get(0).getClableid());
        List<User_info> user_infos = us.queryByUserId(circles.get(0).getUserid());
        List<Review> reviews = rs.queryByComposeid(circleid);
        if(reviews.size() == 0){
            /*说明没有评论*/
        }else{
           /* System.out.println("显示评论数据"+reviews.toString());*/
            for (int i = 0; i < reviews.size(); i++) {
                Map<String, Object> mr = new HashMap<String, Object>();
                List<User_info> lu = us.queryByUserId(reviews.get(i).getUserid());
                List<Reply> replies = rps.queryByReviewid(reviews.get(i).getReviewid());
                if(replies.size() != 0){
                    for (int j = 0; j < replies.size(); j++) {
                        Map<String, Object> ms = new HashMap<String, Object>();
                        ms.put("reviewid",replies.get(j).getReviewid());
                        ms.put("content",replies.get(j).getContent());
                        List<User_info> lu1 = us.queryByUserId(replies.get(j).getFrom_userid());
                        List<User_info> lu2 = us.queryByUserId(replies.get(j).getReplyid());
                        ms.put("from_username",lu1.get(0).getUsername());
                        ms.put("to_username",lu2.get(0).getUsername());
                        ms.put("recoverytime",replies.get(j).getRecoverytime());
                        replie.add(ms);
                    }
                    System.out.println("回复表中的内容"+replie.toString());
                    System.out.println("回复表中的数量"+replie.size());
                }
               /* System.out.println("回复表根据id"+replies.toString());*/
                mr.put("username",lu.get(0).getUsername());
                mr.put("head",lu.get(0).getHead());
                mr.put("count",reviews.get(i).getContent());
                mr.put("time",reviews.get(i).getTime());
                mr.put("num",reviews.size());
                review.add(mr);
            }
        }
        System.out.println("评论表中的数据"+review.toString());
        System.out.println("评论表中的数量"+review.size());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clablename",clables.get(0).getClablename());
        map.put("head",user_infos.get(0).getHead());
        map.put("username",user_infos.get(0).getUsername());
        map.put("ctime",circles.get(0).getTime());
        resultList.add(map);
        mv.addAttribute("list",resultList);
        mv.addAttribute("review",review);
        mv.addAttribute("replie",replie);
        mv.addAttribute("count",review.size()+replie.size());
        System.out.println(circles.get(0).getContent());
        mv.addAttribute("aaa", ForFlie.readFile(circles.get(0).getContent()));
        return  "show";
    }
    @RequestMapping(value = "write/{circleid}")
    public String indexs(Model mv, @PathVariable Integer circleid){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> clm = new ArrayList<Map<String, Object>>();
            List<Circletype> circletypes = cts.queryAll();
            for (int i = 0; i < circletypes.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                Integer tid = circletypes.get(i).getTid();
                List<Clable> clables = cls.queryByTypeid(circletypes.get(i).getTid());
                map.put("tname",circletypes.get(i).getTname());
                map.put("tid",circletypes.get(i).getTid());
                if(clables.size() != 0){
                    for (int j = 0; j < clables.size(); j++) {
                        Map<String, Object> cn = new HashMap<String, Object>();
                        cn.put("tid",clables.get(j).getTypeid());
                        cn.put("clablename",clables.get(j).getClablename());
                        cn.put("clableid",clables.get(j).getClableid());
                        clm.add(cn);
                    }
                }
                resultList.add(map);
            }
            System.out.println(resultList.toString());
            System.out.println(clm.toString());
            mv.addAttribute("list",resultList);
            mv.addAttribute("clms",clm);
        return  "indexs";
    }
    @RequestMapping("add")
    public String add(Model mv,String text){
        return "redirect:/circle/show/1";
    }
}
