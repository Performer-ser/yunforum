package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.*;
import com.aaa.util.ForFlie;
import com.aaa.util.JsonMapper;
import com.aaa.util.TabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
    @Autowired
    CliqueService clqs;
    @Autowired
    ReplyService rps;
    @Autowired
    AdmireService as;

    @RequestMapping("queryLimit")
    @ResponseBody
    public Map<String, Object> query(Integer page, Integer limit) {
        return cs.query(page, limit);
    }

    @RequestMapping("query")
    public String query(Model model){
        List<Circletype> circletypes = cts.queryAll();
        model.addAttribute("lct",circletypes);
        return "./cq";
    }

    @RequestMapping("circle")
    public String testss(Model model,String tab,HttpSession session) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        /*这个是圈子的信息查询出最近10条*/
        List<Circle> lc = null;
        tab = tab==null? "sdf":tab;
        switch (tab){
            case "joined":
                if(null == loginUser){
                    return "logins";
                }
                List<Clique> cliques = clqs.queryByUserid(loginUser.get(0).getUserid());
                //创建Integer数组
                Integer[] clableids = new Integer[cliques.size()];
                for (int i = 0; i < cliques.size(); i++) {
                    //查询出圈子id
                    clableids[i] = cliques.get(i).getClableid();
                }
                lc = cs.diffTimeBy(clableids);
                break;
            case "tech":
                /*根据圈子的类型去查询出*/
                lc = TabUtil.tabul(cls.queryByTypeid(1), cls.queryByTypeid(1).size(), cs);
                break;
            case "creative":
                lc = TabUtil.tabul(cls.queryByTypeid(2), cls.queryByTypeid(2).size(), cs);
                break;
            case "fun":
                lc = TabUtil.tabul(cls.queryByTypeid(3), cls.queryByTypeid(3).size(), cs);
                break;
            case "jobs":
                lc = TabUtil.tabul(cls.queryByTypeid(4), cls.queryByTypeid(4).size(), cs);
                break;
            case "other":
                lc = TabUtil.tabul(cls.queryByTypeid(5), cls.queryByTypeid(5).size(), cs);
                break;
            default:
                lc = cs.diffTime();
                break;
        }
        for (int i = 0; i < lc.size(); i++) {
            Map<String, Object> circle = new HashMap<String, Object>();
            /*这个是获得用户的一些信息*/
            List<Userinfo> lus = us.queryByUserId(lc.get(i).getUserid(),null);
            //System.out.println(lus);
            /*这个是获取评论信息的*/
            List<Review> lr = rs.queryByComposeid(lc.get(i).getCircleid(),3);
            //System.out.println(lr);
            List<Clable> lcs = cls.queryByClableid(lc.get(i).getClableid());
            circle.put("username", lus.get(0).getUsername());
            circle.put("userid", lus.get(0).getUserid());
            circle.put("head", lus.get(0).getHead());
            circle.put("title", lc.get(i).getTitle());
            circle.put("content", lc.get(i).getContent());
            circle.put("circleid",lc.get(i).getCircleid());
            circle.put("clablename", lcs.get(0).getClablename());
            circle.put("cname",lc.get(i).getTime());
            if (lr.size() == 0) {
                circle.put("rcount", 0);
            } else {
                List<Userinfo> lui = us.queryByUserId(lr.get(0).getUserid(),null);
                circle.put("rname", lui.get(0).getUsername());
                /*circle.put("rtime", lr.get(0).getTime());*/
                circle.put("rcount", lr.size());
            }
            resultList.add(circle);
        }
        /*System.out.println(resultList.toString());*/
        model.addAttribute("circ", resultList);
        model.addAttribute("tab", tab);
        return "cir";
    }

    @RequestMapping("querys")
    @ResponseBody
    public String querys(){
        List<Sleft> slefts = cls.queryByselct();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",0);
        map.put("data",slefts);
        String json= JsonMapper.mapToJson(map);
        return json;
    }
    /* 跳转页面*/
    @RequestMapping("find")
    public String find(Model model) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> pci = new ArrayList<Map<String, Object>>();
        /*圈子类型*/
        List<Circletype> lcts = cts.queryAll();
        for (int i = 0; i < lcts.size(); i++) {
            /*圈子标签*/
            List<Clable> lc = cls.queryByTypeid(lcts.get(i).getTid());
            for (int j = 0; j < lc.size(); j++) {
                Map<String, Object> clable = new HashMap<String, Object>();
                List<Circle> lci = cs.queryByClableid(lc.get(j).getClableid());
                clable.put("clablename", lc.get(j).getClablename());
                clable.put("cpic", lc.get(j).getCpic());
                clable.put("count", cs.querycountByClableid(lc.get(j).getClableid()));
                clable.put("tname", lcts.get(i).getTname());
                pci.add(clable);
            }
            Map<String, Object> tname = new HashMap<String, Object>();
            tname.put("tname", lcts.get(i).getTname());
            resultList.add(tname);
        }
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
    public String queryByQuestionid(Model mv, @PathVariable String clablename,Integer page,HttpSession session) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        List<Clable> lc = cls.queryByClablename(clablename);/*圈子名称*/
        Integer queryone = null;
        if(null != userid){
            queryone = clqs.queryone(userid, lc.get(0).getClableid());
        }
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
        map.put("user_infos", us.queryByUserId(lc.get(0).getUserid(),null).get(0));
        map.put("integer", clqs.queryCount(lc.get(0).getClableid(), 1));
        page = page == null ? 1:page;
        List<Circle> circles = cs.queryByClableid2(lc.get(0).getClableid(),(page-1)*10);
        for (int i = 0; i < circles.size(); i++) {
            List<Review> lr = rs.queryByComposeid(circles.get(i).getCircleid(),3);
            List<Userinfo> user_infos = us.queryByUserId(circles.get(i).getUserid(),null);
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
                /*Review{reviewid=3, composeid=25, type=3, content='闪怂的第一次评论25的内容', userid=1, time=Fri Mar 29 10:57:13 CST 2019}*/
                List<Userinfo> lui = us.queryByUserId(lr.get(0).getUserid(),null);
                System.out.println(lui.toString());
                cir.put("rname", lui.get(0).getUsername());
                cir.put("rtime", lr.get(0).getTime());
                cir.put("rcount", lr.size());
            }
            resultList.add(cir);
        }
        Double wq=Double.valueOf(cs.querycountByClableid(lc.get(0).getClableid()));
        Double c=Math.ceil(wq/10);
        for (int j=0;j<c;j++){
            Map<String,Object> m=new HashMap<>();
            m.put("ass",j+1);
            m.put("page",page);
            aw.add(m);
        }
        map.put("reviews", rs.queryByComposeid(lc.get(0).getClableid(),3));
        /*Integer integer = clqs.queryCount(lc.get(0).getClableid(), 0);*//*多少人关注*//*
        List<Circle> circles = cs.queryByClableid(lc.get(0).getClableid());//圈子标签名称
        List<Review> reviews = rs.queryByComposeid(lc.get(0).getClableid());//评论*/
        /*Question list = qs.queryByQuestionid(qid).get(0);
        mv.addAttribute("li",list);
        return "questions/q";*/
        /*8.5 3.5*/
        mv.addAttribute("list", map);
        mv.addAttribute("cir", resultList);
        mv.addAttribute("a",aw);
        mv.addAttribute("queryone",queryone);
        return "general";
    }

    @RequestMapping(value = "attention/{clablename}")
    public String attention(Model mv, @PathVariable String clablename,Integer page,String searchtext){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> aw = new ArrayList<Map<String,Object>>();
        List<Clable> lc = cls.queryByClablename(clablename);
        List<Clique> lcq = clqs.queryByClableid(lc.get(0).getClableid());
        for (int i = 0; i < lcq.size() ; i++) {
            List<Userinfo> user_infos = us.queryByUserId(lcq.get(i).getUserid(),searchtext);
            if(user_infos.size() == 0) break;
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
        mv.addAttribute("list",resultList);
        mv.addAttribute("clablename",clablename);
        return "attention";
    }
    @RequestMapping(value = "search/{clablename}")
    @ResponseBody
    public String search(@PathVariable String clablename,Integer page,String searchtext){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Clable> lc = cls.queryByClablename(clablename);
        List<Clique> lcq = clqs.queryByClableid(lc.get(0).getClableid());
        List<Userinfo> user_infos = us.queryByUserId(null,searchtext);
        for (int i = 0; i < lcq.size() ; i++) {
            if(user_infos.size() == 0) break;
            if(lcq.get(i).getUserid() != user_infos.get(0).getUserid())break;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userid",user_infos.get(0).getUserid());
            map.put("username",user_infos.get(0).getUsername());
            map.put("head",user_infos.get(0).getHead());
            List<Circle> circles = cs.queryByUserid(user_infos.get(0).getUserid());
            map.put("topic",circles.size());
            List<Review> reviews = rs.queryByUserid(user_infos.get(0).getUserid());
            map.put("comments",reviews.size());
            List<Clique> cliques = clqs.queryByUserid(user_infos.get(0).getUserid());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            map.put("ctime",sdf.format(cliques.get(0).getCtime()));
            resultList.add(map);
        }
        System.out.println(resultList.toString());
        String sea = "";
        if(user_infos.size() != 0){
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> stringObjectMap = resultList.get(i);
                sea += "<tr class=\"group__detail--member mt15\"><td><img src=\""+stringObjectMap.get("head")+"\" class=\"img-circle\" style=\"width:32px\">" +
                        "<div class=\"member--info mt5\"><span class=\"user-member-name\"><a href=\"/circle/personal/"+stringObjectMap.get("userid")+"\">"+stringObjectMap.get("username")+"</a>" +
                        "</span></div></td><td>"+stringObjectMap.get("topic")+"</td><td>"+stringObjectMap.get("comments")+"</td>" +
                        "<td>"+stringObjectMap.get("ctime")+"</td><td></td></tr>";
            }
        }
        System.out.println(sea.toString());
        return sea.toString();
    }
    @RequestMapping(value = "personal/{userid}")
    public String personal(Model mv, @PathVariable Integer userid){
        System.out.println("访问个人页面");
        return  "personal";
    }
    @RequestMapping(value = "show/{circleid}")
    public String attention(Model mv, @PathVariable Integer circleid,HttpSession session){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> review = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> replie = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> lmc = new ArrayList<Map<String, Object>>();
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        List<Circle> circles = cs.queryByCircleid(circleid);
        List<Clable> clables = cls.queryByClableid(circles.get(0).getClableid());
        List<Userinfo> user_infos = us.queryByUserId(circles.get(0).getUserid(),null);
        List<Review> reviews = rs.queryByComposeid(circleid,3);
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
                mr.put("likenum",as.queryCount(reviews.get(i).getReviewid(), 3));
                mr.put("username",lu.get(0).getUsername());
                mr.put("userid",lu.get(0).getUserid());
                mr.put("head",lu.get(0).getHead());
                Integer queryone = as.queryone(reviews.get(i).getReviewid(), 3, userid);
                mr.put("queryone",queryone);
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
        map.put("title",circles.get(0).getTitle());
        map.put("circleid",circles.get(0).getCircleid());
        resultList.add(map);
        List<Circle> circles1 = cs.queryByClableid2(clables.get(0).getClableid(), 0);
        for (int i = 0; i < circles1.size(); i++) {
            Map<String, Object> mc = new HashMap<String, Object>();
            mc.put("title",circles1.get(i).getTitle());
            mc.put("circleid",circles1.get(i).getCircleid());
            lmc.add(mc);
        }
        mv.addAttribute("list",resultList);
        System.out.println(resultList.toString());
        mv.addAttribute("review",review);
        mv.addAttribute("replie",replie);
        mv.addAttribute("clablename",clables.get(0).getClablename());
        mv.addAttribute("count",review.size()+replie.size());
        mv.addAttribute("aaa", ForFlie.readFile(circles.get(0).getContent()));
        mv.addAttribute("lmc", lmc);
        return  "show";
    }
    @RequestMapping(value = "write/{circleid}")
    public String indexs(Model mv, @PathVariable Integer circleid,HttpSession session){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        if(userid == 0)return "redirect:../../logins";
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
    @RequestMapping("addByReview")
    public String addByReview(Model mv, String text, HttpSession session,Integer circleid){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        if(userid == 0)return "logins";
        Review r = new Review();
        r.setContent(text);
        r.setComposeid(circleid);
        /*从session中获取获取用户的id*/
        r.setUserid(userid);
        r.setType(3);
        rs.addByReview(r);
        return "redirect:/circle/show/"+circleid;
    }
    @RequestMapping("addByReply")
    public String addByReply(Model mv, String text, HttpSession session,Integer reviewid,Integer touserid,Integer circleid){
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
        return "redirect:/circle/show/"+circleid;
    }
    @RequestMapping("add")
    @ResponseBody
    public int add(Clable clable){
        return cs.addcl(clable);
    }
    @RequestMapping("update")
    @ResponseBody
    public int update(Clable clable){
        return cs.update(clable);
    }
    @RequestMapping("delete")
    @ResponseBody
    public int delete(Integer clableid){
        return cs.delete(clableid);
    }
}
