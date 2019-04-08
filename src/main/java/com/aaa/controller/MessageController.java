package com.aaa.controller;

import com.aaa.entity.Userinfo;
import com.aaa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mess")
public class MessageController {
    @Autowired
    MessageService ms;
    @RequestMapping("queryMess")
    public String quqeryMess(Model model, HttpSession session){
        List<Userinfo> lu=(List) session.getAttribute("LoginUser");
        List<Map<String,Object>> lm=ms.queryMess(lu.get(0).getUserid());
        System.out.printf(lm.toString());
        model.addAttribute("mess",lm);
        return "message";
    }
}
