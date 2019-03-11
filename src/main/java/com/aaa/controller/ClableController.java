package com.aaa.controller;

import com.aaa.service.ClableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@Controller
public class ClableController {
    @Autowired
    ClableService cs;
    @RequestMapping("queryLimit")
    @ResponseBody
    public Map<String,Object> query(Integer page, Integer limit){
        return cs.query(page,limit);
    }

}
