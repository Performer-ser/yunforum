package com.aaa.controller;

import com.aaa.entity.Lable;
import com.aaa.service.LableService;
import com.aaa.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
        List<Map<String,Object>> lb=ls.query();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",0);
        map.put("data",lb);
        String json= JsonMapper.mapToJson(map);
        System.out.printf(json);
        return json;
    }

    @RequestMapping("delete")
    @ResponseBody
    public int delete(Integer lableid){
        return ls.delete(lableid);
    }
    @RequestMapping("add")
    @ResponseBody
    public int add(Lable lable, @RequestParam("pic")MultipartFile pic, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int i=0;
        System.out.printf(lable.toString());
        if(pic!=null){
        String originalFilename = pic.getOriginalFilename();
        String fileName = "C:\\Users\\25059\\Downloads\\picture";
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File newFile = new File(fileName + "/" + originalFilename);
        pic.transferTo(newFile);
        lable.setLablepic(originalFilename);
        i=ls.add(lable);
    }else{
            lable.setLablepic(null);
        i=ls.add(lable);
        }
        return i;
    }
    @RequestMapping("update")
    @ResponseBody
    public int update(Lable lable,@RequestParam("pic")MultipartFile pic,HttpServletRequest request, HttpServletResponse response) throws IOException {
        int i=0;
        if(pic!=null){
            String originalFilename = pic.getOriginalFilename();
            String fileName = "C:\\Users\\25059\\Downloads\\picture";
            File file = new File(fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            File newFile = new File(fileName + "/" + originalFilename);
            pic.transferTo(newFile);
            lable.setLablepic(originalFilename);
            i=ls.update(lable);
        }else{
            lable.setLablepic(null);
            i=ls.update(lable);
        }
        return i;
    }

}
