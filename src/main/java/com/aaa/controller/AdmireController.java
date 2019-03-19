package com.aaa.controller;

import com.aaa.service.AdmireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admire")
public class AdmireController {
    @Autowired
    AdmireService as;
    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> query(Integer type_id, Integer type, Integer userid){
        List<Map<String,Object>> lm=as.query(type_id,type,userid);
        Map map=new HashMap();
        map.put("count",lm.get(0).get("count"));
        map.put("status",lm.get(0).get("status"));

        return map;

    }
    @RequestMapping("update")
    @ResponseBody
    public Map<String,Object> update(Integer type_id, Integer type, Integer userid){
        Map map=new HashMap();
        int message=0;
        Integer status=as.queryone(type_id,type,userid);
        if(status==null){
            message=as.add(type_id,type,userid);
        }else if(status==0){
            message=as.update(type_id,type,userid,1);
        }else{
            message=as.update(type_id,type,userid,0);
        }
        List<Map<String,Object>> lm=as.query(type_id,type,userid);
        map.put("count",lm.get(0).get("count"));
        map.put("status",lm.get(0).get("status"));

        return map;
    }
}
