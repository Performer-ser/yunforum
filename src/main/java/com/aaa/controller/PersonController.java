package com.aaa.controller;

import com.aaa.entity.*;
import com.aaa.service.*;
import com.aaa.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
    UserinfoService us;
    @Autowired
    LableService ls;
    @Autowired
    LetterService les;
    @Autowired
    CliqueService cs;
    @Autowired
    ClableService cls;
    @RequestMapping("queryPS")
    public String queryPS(Model model, HttpSession session,Integer  userid){
        uuuu(userid,model,session);
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
            lms.get(i).put("perspid",ss.queryAd(userid).get(0).get("specialid"));
        }
        model.addAttribute("lm",lm);
        model.addAttribute("lms",lms);
        return "personarticle";
    }
    @RequestMapping("answers")
    public String answers(Integer page,Model model,Integer userid,HttpSession session){
        uuuu(userid,model,session);
        if(page==null){
            page=1;
        }
        List<Map<String,Object>> lm=rs.queryOneAll(userid,(page-1)*20);
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        for (int i=0;i<lm.size();i++){
            Map<String,Object> m=qs.queryTitle(Integer.valueOf(lm.get(i).get("composeid").toString()));
            List<Map<String,Object>> ll=as.query(Integer.valueOf(lm.get(i).get("reviewid").toString()),4,userid);
            lm.get(i).put("title",m.get("title"));
            lm.get(i).put("caina",m.get("reviewid"));
            lm.get(i).put("admire",ll.get(0).get("count"));
            Map<String,Object> q=new HashMap<>();
        }
        Double wq=Double.valueOf(rs.queryUserAll(userid).size());
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
    public String qqq(Integer page,Model model,Integer userid,HttpSession session){
        uuuu(userid,model,session);
        List<Question> ls=qs.queryUser(userid,(page-1)*20);
        List<Map<String,Object>> lq=new ArrayList<>();
        List<Map<String,Object>> aw=new ArrayList<Map<String,Object>>();
        System.out.printf(ls.toString());
        for (int i=0; i<ls.size();i++){
            List<Map<String,Object>> ll=as.query(Integer.valueOf(ls.get(i).getQuestionid().toString()),1,userid);
            Map map=new HashMap();
            map.put("questionid",ls.get(i).getQuestionid());
            map.put("title",ls.get(i).getTitle());
            map.put("reviewid",ls.get(i).getReviewid());
            map.put("time",ls.get(i).getTime());
            map.put("admire",ll.get(0).get("count"));
            lq.add(map);
        }
        Double wq=Double.valueOf(qs.queryUserAll(userid).size());
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
    public void uuuu(Integer userid,Model model,HttpSession session){
        Map map=new HashMap();
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        if(login!=null){
            login.get(0).setHead(us.queryOne(userid).getHead());
        }
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
    @RequestMapping("queryattla")
    public String queryaattla(Integer userid,Model model,HttpSession session){
        uuuu(userid,model,session);
        List<Integer> li=ms.querylaid(userid);
        List<Map<String,Object>> lm=new ArrayList<>();
        for (int i=0;i<li.size();i++){
            Lable lable=ls.queryById(li.get(i));
            Map map=new HashMap();
            map.put("lableid",lable.getLableid());
            map.put("lablename",lable.getLablename());
            map.put("describe",lable.getDescribe().substring(0,50)+"...");
            map.put("guanzhu",ms.lableAll(lable.getLableid()));
            lm.add(map);
        }
        model.addAttribute("lm",lm);
        return "attentionlable";
    }
    @RequestMapping("queryattuser")
    public String queryattuser(Integer userid,Model model,HttpSession session){
        uuuu(userid,model,session);
        List<Integer> li=ms.queryto(userid);
        List<Map<String,Object>> lm=new ArrayList<>();
        for (int i=0;i<li.size();i++){
            Userinfo userinfo=us.queryOne(li.get(i));
            Map map=new HashMap();
            map.put("userid",userinfo.getUserid());
            map.put("username",userinfo.getUsername());
            map.put("head",userinfo.getHead());
            map.put("guanzhu",ms.tome(userinfo.getUserid()));
            lm.add(map);
        }
        model.addAttribute("lm",lm);
        return "attentionuser";
    }
    @RequestMapping("queryattspe")
    public String queryattspe(Integer userid,Model model,HttpSession session){
        uuuu(userid,model,session);
        List<Integer> sm=ms.myspe(userid);
        List<Map<String,Object>> lm=new ArrayList<>();
        for (int i=0; i<sm.size();i++){
            Map m=ms.perspeAll(sm.get(i));
            int j=ss.queryAtt(sm.get(i));
            m.put("guanzhu",j);
            int k=ms.countSpe(sm.get(i));
            m.put("shuliang",k);
            lm.add(m);
        }
        model.addAttribute("lm",lm);
        return "attentionspe";
    }

    @RequestMapping("queryattque")
    public String queryattque(Integer userid,Model model,HttpSession session){
        uuuu(userid,model,session);
        List<Integer> li=ms.userQues(userid);
        List<Map<String,Object>> lm=new ArrayList<>();
        for (int i=0;i<li.size();i++){
            Question qq=ms.selectQues(li.get(i));
            Integer j=ms.reQues(li.get(i));
            Integer k=ms.guanQue(li.get(i));
            Map map=new HashMap();
            map.put("title",qq.getTitle());
            map.put("questionid",qq.getQuestionid());
            map.put("huida",j);
            map.put("guanzhu",k);
            lm.add(map);
        }
        model.addAttribute("lm",lm);
        return "attentionques";
    }
    @RequestMapping("queryOne")
    public String queryOne(Integer userid, Model model,HttpSession session){
        uuuu(userid,model,session);
        model.addAttribute("lu",us.queryOne(userid));
        return "xinxi";
    }
    @RequestMapping("updateuser")
    public String updateuser(@RequestParam MultipartFile avatar,Userinfo u,HttpSession session) throws IOException {
        List<Userinfo> login=(List<Userinfo>) session.getAttribute("LoginUser");
        u.setUserid(login.get(0).getUserid());
        String randomIdByUUID = IdUtils.getRandomIdByUUID();
        String fileName="D:\\S3\\ggg\\segmentfault.com\\text";
        File file=new File(fileName);
        if(!file.exists()){
            file.mkdirs();
        }
        if(u.getBirthday().equals("")){
            u.setBirthday(null);
        }
        File newFile=new File(fileName+"/"+randomIdByUUID);
        avatar.transferTo(newFile);
        u.setHead(randomIdByUUID);
        Integer jj=us.userup(u);
        List<Userinfo> uu=new ArrayList<>();
        uu.add(u);
        session.setAttribute("LoginUser",uu);
        return "redirect:queryOne?userid="+u.getUserid();
    }
    @RequestMapping("queryonespe")
    public String queryonespe(Model model,Integer perspid){
        Map m=ms.perspeAll(perspid);
        int j=ss.queryAtt(perspid);
        m.put("guanzhu",j);
        int k=ms.countSpe(perspid);
        m.put("shuliang",k);
        m.put("describe",ms.QueDescribe(perspid).get("perspdescribe"));
        m.put("userid",us.queryOne(Integer.valueOf(ms.QueDescribe(perspid).get("userid").toString())).getUserid());
        m.put("username",us.queryOne(Integer.valueOf(ms.QueDescribe(perspid).get("userid").toString())).getUsername());
        m.put("truename",us.queryOne(Integer.valueOf(ms.QueDescribe(perspid).get("userid").toString())).getTruename());
        m.put("name",ms.QueDescribe(perspid).get("perspname"));
        List<Map<String,Object>> lm=ms.userQue(perspid);
        model.addAttribute("map",m);
        model.addAttribute("lm",lm);
        return "zhuanlan";
    }
    @RequestMapping("queryzi")
    public String queryzi(String zi,Model model){
        List<Question> lq=ms.queryzi(zi);
        model.addAttribute("lq",lq);
        return "search";
    }
}
