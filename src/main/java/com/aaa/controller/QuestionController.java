package com.aaa.controller;

import com.aaa.entity.Qlable;
import com.aaa.entity.Question;
import com.aaa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
/*@RequestMapping("ques")*/
public class QuestionController {
    @Autowired
    QuestionService qs;

    Question ques = new Question();
    @RequestMapping("questions")
    public String queryQuestionsByQlable(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        return "questions/questions";
    }
    @RequestMapping("subscribed")
    public String b(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        System.out.println("就去了为我推送页面");
        return "questions/subscribed";
    }
    @RequestMapping("unanswered")
    public String c(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        System.out.println("就去了等待回答页面");
        return "questions/unanswered";
    }
    @RequestMapping("hottest")
    public String d(Model model){
        List<Map<String, Object>> list = qs.queryQuestionsByQlable();
        model.addAttribute("list",list);
        System.out.println("就去了热门回答页面");
        return "questions/hottest";
    }
    @RequestMapping(value="q/{qid}")
    public String quesById(Model model,@PathVariable Integer qid){
        List<Map<String, Object>> maps = qs.quesById(qid);
        Map<String, Object> list = qs.quesById(qid).get(0);
        model.addAttribute("maps",maps);
        model.addAttribute("list",list);
        System.out.println(maps.toString());
        return "questions/q";
    }
    /*@RequestMapping(value = "q/{qid}")
    public ModelAndView queryByQuestionid(ModelAndView mv ,@PathVariable Integer qid){
        List<Question> list = qs.queryByQuestionid(qid);
        mv.addObject("qid",list);
        mv.setViewName("questions/q");
        return mv;
    }*/
    /*@RequestMapping(value = "q/{qid}")
    public String queryByQuestionid(Model mv ,@PathVariable Integer qid){
        Question list = qs.queryByQuestionid(qid).get(0);
        mv.addAttribute("li",list);
        return "questions/q";
    }*/


}
