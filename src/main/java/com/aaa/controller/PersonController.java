package com.aaa.controller;

import com.aaa.entity.Clable;
import com.aaa.entity.Userinfo;
import com.aaa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("person")
public class PersonController {
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
    CliqueService cs;
    @Autowired
    ClableService cls;
    @RequestMapping("queryPS")
    public String queryPS(Model model, HttpSession session,Integer  userid){
        List<Map<String,Object>> lm=ss.queryPS(userid);
        System.out.printf(lm.toString());
        for (int i=0;i<lm.size();i++){
            int j=ss.queryAtt(Integer.valueOf(lm.get(i).get("perspid").toString()));
            lm.get(i).put("guanzhu",j);
        }
        List<Map<String,Object>> lms=ss.queryAd(userid);
        for (int i=0;i<lms.size();i++){
            int j=ss.queryAA(Integer.valueOf(lms.get(i).get("specialid").toString()));
            lms.get(i).put("dz",j);
        }
        model.addAttribute("lm",lm);
        model.addAttribute("lms",lms);
        return "personarticle";
    }
    @RequestMapping("answers")
    public String answers(Integer page,Model model,Integer userid){
        uuuu(userid,model);
        if(page==null){
            page=1;
        }
        List<Map<String,Object>> lm=rs.queryOneAll(userid,(page-1)*20);
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        for (int i=0;i<lm.size();i++){
            Map<String,Object> m=qs.queryTitle(Integer.valueOf(lm.get(i).get("composeid").toString()));
            List<Map<String,Object>> ll=as.query(Integer.valueOf(lm.get(i).get("reviewid").toString()),4,1);
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
    @RequestMapping("qqq")
    public String qqq(Integer page,Model model){
        List<Map<String,Object>> lq=qs.queryUser(1,(page-1)*20);
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        for (int i=0; i<lq.size();i++){
            List<Map<String,Object>> ll=as.query(Integer.valueOf(lq.get(i).get("questionid").toString()),1,1);
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
        Map map=new HashMap();
        map.put("userid",userid);
        map.put("anwser",qs.queryAllAnwser(userid).size());
        map.put("question",qs.queryUserAll(userid).size());
        map.put("guanzhu",1111);
        map.put("fensi",1111);
        map.put("wenzhang",ss.queryAd(userid).size());
        model.addAttribute("map",map);
    }
    @RequestMapping("attentionuser")
    @ResponseBody
    public int attentionuser(Integer userid,HttpSession session){
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        int result=0;
        if(ms.queryAllUser(login.get(0).getUserid(),userid).size()==0){
            result=ms.addUser(login.get(0).getUserid(),userid);
        }else{
            ms.delUser(login.get(0).getUserid(),userid);
            result=0;
        }
        return result;
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
    @RequestMapping("circle")
    @ResponseBody
    public String circle(String clablename,HttpSession session){
        List<Userinfo> loginUser = (List<Userinfo>) session.getAttribute("LoginUser");
        Integer userid = null == loginUser? 0:loginUser.get(0).getUserid();
        if (userid == 0)return "logins";
        List<Clable> lc = cls.queryByClablename(clablename);
        Integer clableid = lc.get(0).getClableid();
        Integer status = cs.queryone(userid,clableid);
        Integer message = null;
        if(status==null){
            message=cs.add(clableid,userid);
        }else if(status==0){
            message=cs.update(clableid,userid,1);
        }else{
            message=cs.update(clableid,userid,0);
        }
        return message+"";
    }
    @RequestMapping("attentionque")
    @ResponseBody
    public int attentionque(Integer questionid,HttpSession session){
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
