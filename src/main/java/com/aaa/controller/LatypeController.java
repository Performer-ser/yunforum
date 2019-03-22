package com.aaa.controller;

import com.aaa.entity.Latype;
import com.aaa.service.LatypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("latype")
public class LatypeController {
    @Autowired
    LatypeService lts;
    @RequestMapping("query")
    public String query(Model model){
        List<Latype> ll=lts.query();
        model.addAttribute("ll",ll);
        return "lable";
    }
}
