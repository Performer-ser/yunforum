package com.aaa.controller;

import com.aaa.entity.Letter;
import com.aaa.entity.Userinfo;
import com.aaa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("letter")
public class LetterController {
    @Autowired
    LetterService ls;
    @Autowired
    SpecialService ss;
    @Autowired
    QuestionService qs;
    @Autowired
    ReviewService rs;
    @Autowired
    AdmireService as;
    @Autowired
    MessageService ms;
    @Autowired
    UserinfoService us;
    @Autowired
    LetterService les;
    @Autowired
    CliqueService cs;
    @Autowired
    ClableService cls;
    public void uuuu(Integer userid,Model model,HttpSession session){
        Map map=new HashMap();
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        map.put("person",us.queryOne(userid));
        map.put("userid",userid);
        map.put("anwser",qs.queryAllAnwser(userid).size());
        map.put("question",qs.queryUserAll(userid).size());
        map.put("guanzhu",ms.fromme(userid));
        map.put("fensi",ms.tome(userid));
        map.put("wenzhang",ss.queryAd(userid).size());
        map.put("sixin",les.queryNo(userid));
        model.addAttribute("map",map);
    }
    @RequestMapping("query")
    public String query(Model model, HttpSession session){
        List<Map<String,Object>> lm=new ArrayList<>();
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = loginUser.get(0).getUserid();
        uuuu(loginUser.get(0).getUserid(),model,session);
        List<Integer>  lt=ls.queryFromid(userid);
        for (int i=0;i<lt.size();i++){
            Letter l=ls.queryLe(lt.get(i));
            Map map=new HashMap();
            Userinfo from=ls.queryuserinfo(l.getFromid());
            map.put("fromid",l.getFromid());
            map.put("fromuser",from.getUsername());
            map.put("head",from.getHead());
            map.put("content",l.getContent());
            map.put("status",l.getStatus());
            Userinfo to=ls.queryuserinfo(userid);
            Integer count=ls.queryStatus(l.getFromid(),l.getToid());
            map.put("toid",userid);
            map.put("touser",to.getUsername());
            map.put("time",l.getTime());
            map.put("count",count);
            lm.add(map);
        }
        System.out.printf(lm.toString());
        model.addAttribute("lm",lm);
        return "letter";
    }
    @RequestMapping("details")
    public String details(Integer fromid,Model model,HttpSession session){
        List<Userinfo> user=(List<Userinfo>) session.getAttribute("LoginUser");
        List<Map<String,Object>>  ld=ls.queryDetial(fromid,user.get(0).getUserid());
        uuuu(user.get(0).getUserid(),model,session);
        int jj=ls.upstatus(fromid,user.get(0).getUserid());
        Userinfo uf=ls.queryuserinfo(fromid);
        model.addAttribute("ld",ld);
        model.addAttribute("uf",uf);
        return "ledetails";
    }
    @RequestMapping("add")
    public String add(Integer toid,String tttt,HttpSession session){
        List<Userinfo> user=(List<Userinfo>) session.getAttribute("LoginUser");
        ls.add(user.get(0).getUserid(),toid,tttt);
        return "redirect:details?fromid="+toid;
    }
    @RequestMapping("updateAll")
    public String updateAll(HttpSession session){
        List<Userinfo> user=(List<Userinfo>) session.getAttribute("LoginUser");
        int jj=ls.updateAll(user.get(0).getUserid());
        return "redirect:query";
    }
}
