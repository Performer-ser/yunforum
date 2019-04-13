package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.PerspecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("perspecial")
public class Perspecialcontroller {
   @Autowired
   PerspecialService pes;

   @RequestMapping("add")
   public String add(Perspecial per){
        return "add";
   }
    @RequestMapping("adds")
    @ResponseBody
    public Integer adds(Perspecial per,HttpSession session){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        //per.setUserid(1);
        per.setUserid(login.get(0).getUserid());
        System.out.println(per.getPerspdescribe());
        System.out.println(per.getPerspname());
        pes.add(per);
        return 1;
    }
    @RequestMapping("perspecial")
    public String queryPerspecial(Model m,HttpSession session,Integer userid){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
       List<Perspecial> list= pes.queryPerspecial(login.get(0).getUserid());
       m.addAttribute("list",list);
        List<Latype> list1 = pes.queryLatype();

            List<Lable> lists = pes.queryLable();
            m.addAttribute("lists",lists);
            System.out.println("lists"+lists);

        m.addAttribute("list1",list1);
        System.out.println("list1"+list1);

      return "write";
    }
    @RequestMapping("add2")
    @ResponseBody
    public Integer add2(HttpSession session,Special spe, String lablename){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        spe.setLableid(pes.querylablename(lablename).get(0).getLableid());
        spe.setUserid(login.get(0).getUserid());
        pes.addspe(spe);
       return 1;
    }

}
