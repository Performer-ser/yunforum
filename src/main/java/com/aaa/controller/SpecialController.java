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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    ReviewService rs;
    @Autowired
    User_infoService us;
    @Autowired
    ReplyService rps;
    @Autowired
    AdmireService as;
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
        return "indexss";
    }
    @RequestMapping("querydz")
    public String querydz(Model m){
        List<Map<String,Object>> list= ss.querydz();
        m.addAttribute("list",list);
        return "hottest";
    }

    @RequestMapping(value = "a/{specialid}")
    public String queryByid(Model m ,@PathVariable Integer specialid,HttpSession session){

        List<Map<String, Object>> zlidlist= ss.queryZLid(specialid);
        Integer zlid=(Integer)zlidlist.get(0).get("perspid");
        m.addAttribute("zlid",zlid);
        List<Map<String, Object>> list = ss.queryByid(specialid);

        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        List<Map<String, Object>> replie = new ArrayList<Map<String, Object>>();
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        List<Map<String, Object>> review = new ArrayList<Map<String, Object>>();
        m.addAttribute("list",list);
        List<Map<String,Object>> querya= ss.querya();
        List<Review> reviews = rs.queryByComposeid(specialid,2);
        if(reviews.size() == 0){
            /*说明没有评论*/
        }else{
            /* System.out.println("显示评论数据"+reviews.toString());*/
            for (int i = 0; i < reviews.size(); i++) {
                List<Reply> replies = rps.queryByReviewid(reviews.get(i).getReviewid());
                Map<String, Object> mr = new HashMap<String, Object>();
                List<Userinfo> lu = us.queryByUserId(reviews.get(i).getUserid(),null);
                if(replies.size() != 0){
                    for (int j = 0; j < replies.size(); j++) {
                        Map<String, Object> ms = new HashMap<String, Object>();
                        Integer query = as.queryCount(replies.get(j).getReplyid(), 2);
                        ms.put("likenumByReply",query);
                        ms.put("replyid",replies.get(j).getReplyid());
                        System.out.println("sfgdhf"+replies.get(j).getReplyid());
                        Integer queryone = as.queryone(replies.get(j).getReplyid(), 2, userid);
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
                mr.put("likenum",as.queryCount(reviews.get(i).getReviewid(), 2));
                mr.put("username",lu.get(0).getUsername());
                mr.put("userid",lu.get(0).getUserid());
                /*mr.put("head",lu.get(0).getHead());*/
                Integer queryone = as.queryone(reviews.get(i).getReviewid(), 2, userid);
                mr.put("queryone",queryone);
                mr.put("count",reviews.get(i).getContent());
                mr.put("time",reviews.get(i).getTime());
                mr.put("num",reviews.size());
                review.add(mr);
            }
        }
        /*System.out.println("评论表中的数据"+review.toString());
        System.out.println("评论表中的数量"+review.size());*/
        m.addAttribute("querya",querya);
        m.addAttribute("review",review);
        m.addAttribute("replie",replie);
        m.addAttribute("count",review.size()+replie.size());
        System.out.println(querya);
        return "a";
    }

    @RequestMapping("clickGuanZhu")
    @ResponseBody
    public Integer clickGuanZhu(Integer zlid,HttpSession session){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        List<Map<String, Object>> list=ss.querGuanZhu(loginUser.get(0).getUserid(),zlid);

        if(list.size()>0){
            return 1;
        }else{
            return 0;
        }
    }

    @RequestMapping("addByReview")
    public String addByReview(Model mv, String text, HttpSession session,Integer specialid){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        if(userid == 0)return "logins";
        Review r = new Review();
        r.setContent(text);
        r.setComposeid(specialid);
        /*从session中获取获取用户的id*/
        r.setUserid(userid);
        r.setType(2);
        rs.addByReview(r);
        return "redirect:/specials/a/"+specialid;
    }
    @RequestMapping("addByReply")
    public String addByReply(Model mv, String text, HttpSession session,Integer reviewid,Integer touserid,Integer specialid){
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
        return "redirect:/specials/a/"+specialid;
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
       return "blogs/hottests";
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
