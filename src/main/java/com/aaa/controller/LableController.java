package com.aaa.controller;

import com.aaa.entity.Lable;
import com.aaa.service.LableService;
import com.aaa.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("lable")
public class LableController {
    @Autowired
    LableService ls;
    @RequestMapping("query")
    @ResponseBody
    public String query(HttpServletResponse resp) throws IOException {
        List<Lable> lb=ls.query();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",0);
        map.put("data",lb);
        String json= JsonMapper.mapToJson(map);
        System.out.printf(json);
        return json;
    }
}
