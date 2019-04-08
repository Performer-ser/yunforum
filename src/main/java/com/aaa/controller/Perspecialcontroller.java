package com.aaa.controller;

import com.aaa.entity.Latype;
import com.aaa.entity.Perspecial;
import com.aaa.entity.Special;
import com.aaa.service.PerspecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Integer adds(Perspecial per){
        per.setUserid(1);
        System.out.println(per.getPerspdescribe());
        System.out.println(per.getPerspname());
        pes.add(per);
        return 1;
    }
    @RequestMapping("perspecial")
    public String queryPerspecial(Model m){
       List<Perspecial> list= pes.queryPerspecial();
       m.addAttribute("list",list);
        List<Latype> list1 = pes.queryLatype();
        for (Latype lt:list1){
            String s = lt.getLatypename().toString();
            List<Map<String, Object>> lists = pes.queryLableByLatypeid(s);
            m.addAttribute("lists",lists);
            System.out.println("lists"+lists);
        }
        m.addAttribute("list1",list1);
        System.out.println("list1"+list1);

      return "write";
    }
    @RequestMapping("add2")
    @ResponseBody
    public Integer add2(Special spe,String lableName){
        System.out.println("spe = " + spe);
        System.out.println("lableName = " + lableName);
                /*spe.setLableid(5)*/;
        spe.setUserid(1);
        pes.addspe(spe);
       return 1;
    }

}
