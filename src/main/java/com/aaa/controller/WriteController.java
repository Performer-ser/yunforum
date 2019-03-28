package com.aaa.controller;

import com.aaa.entity.Circle;
import com.aaa.service.CircleService;
import com.aaa.util.ForFlie;
import com.aaa.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("write")
public class WriteController{
    @Autowired
    CircleService cs;

    @RequestMapping("text")
    @ResponseBody
    public int text(String text,String title,Integer clableid) throws IOException{
        int add = 0;
        String randomIdByUUID = IdUtils.getRandomIdByUUID();
        if(ForFlie.createFile(randomIdByUUID,text)){
           Circle c = new Circle();
           c.setClableid(clableid);
           c.setContent(randomIdByUUID);
           c.setTitle(title);
           /*从session里面获取，后期添加*/
           c.setUserid(1);
           add = cs.add(c);
           System.out.println(add);
       }
        return add;
    }

    @RequestMapping("pic")
    @ResponseBody
    public Map<String,Object> pic(@RequestParam("editormd-image-file") MultipartFile files, HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String originalFilename = files.getOriginalFilename();
        String fileName="D:\\S3\\ggg\\segmentfault.com\\text";
        File file=new File(fileName);
        if(!file.exists()){
            file.mkdirs();
        }
        File newFile=new File(fileName+"/"+originalFilename);
        files.transferTo(newFile);
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        Map map=new HashMap();
        map.put("success",1);
        map.put("message","asdof");
        map.put("url",basePath+"image/"+originalFilename);
        System.out.printf(map.toString());
        //response.getWriter().write("{\"success\":1,\"message\":\"upload successful\",\"url\":\""+basePath+"image/"+originalFilename+"\"}");
        return map;
    }
    @RequestMapping("ReadFile")
    //@ResponseBody
    public String ReadFile(String title, Model model, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        model.addAttribute("aaa",ForFlie.readFile(title));
        return "index";
    }
}
