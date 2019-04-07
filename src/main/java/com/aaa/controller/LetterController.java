package com.aaa.controller;

import com.aaa.entity.Letter;
import com.aaa.entity.Userinfo;
import com.aaa.service.LetterService;
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
    @RequestMapping("query")
    public String query(Model model, HttpSession session){
        List<Map<String,Object>> lm=new ArrayList<>();
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = loginUser.get(0).getUserid();
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
        Userinfo uf=ls.queryuserinfo(fromid);
        System.out.println("asdfasdfdsfdsafdasfdasfafdsfasfdsafdsafdas"+ld);
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
}
