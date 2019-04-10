package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.*;
import com.aaa.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        List<Lable> listlables=lts.querylables();
        m.addAttribute("listlables",listlables);
        List<Map<String,Object>> queryxg=lts.queryxg((Integer)  list.get(0).get("latypeid"));
        m.addAttribute("queryxg",queryxg);
        return "t";
    }

    @RequestMapping("clickbiaoqian")
    @ResponseBody
    public Integer queryBiaoqian(Integer lableid,HttpSession session){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        List<Map<String, Object>> list=ss.querGuanZhu(loginUser.get(0).getUserid(),lableid);
        if(list.size()>0){
            return 1;
        }else{
            return 0;
        }
    }


    @RequestMapping(value = "a/{specialid}")
    public String queryByid(Model m ,@PathVariable Integer specialid,HttpSession session){
        List<Map<String, Object>> list = ss.queryByid(specialid);
        System.out.println("sdfdslist"+list.toString());
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

    @RequestMapping("attentionspe")
    @ResponseBody
    public int attentionspe(Integer perspid, HttpSession session){
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

    @RequestMapping("attentionlab")
    @ResponseBody
    public int attentionlab(Integer lableid,HttpSession session){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        int result=0;
        if(ms.queryAllLab(lableid,login.get(0).getUserid()).size()==0){
            result=ms.addLab(lableid,login.get(0).getUserid());
        }else{
            ms.delLab(lableid,login.get(0).getUserid());
            result=0;
        }
        return result;
    }

}
