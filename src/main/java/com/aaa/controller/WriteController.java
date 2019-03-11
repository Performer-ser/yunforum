package com.aaa.controller;
import com.aaa.entity.Question;
import com.aaa.service.QuestionService;
import com.aaa.util.ForFlie;
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

@Controller
@RequestMapping("write")
public class WriteController{

    @Autowired
    QuestionService qs;

    @RequestMapping("text")
    @ResponseBody
    public void text(String text,String title){
       if(ForFlie.createFile(title,text)){
           Question q=new Question();
           q.setTitle(title);
           q.setContent(title);
           qs.add(q);
       }

    }

    @RequestMapping("pic")
    @ResponseBody
    public void pic(@RequestParam("editormd-image-file") MultipartFile files,HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String originalFilename = files.getOriginalFilename();
        String fileName="C:\\Users\\25059\\Downloads\\picture";
        File file=new File(fileName);
        if(!file.exists()){
            file.mkdirs();
        }
        File newFile=new File(fileName+"/"+originalFilename);
        files.transferTo(newFile);
        String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        response.getWriter().write("{\"success\":1,\"messgae\":\"upload successful\",\"url\":\""+basePath+"image/"+originalFilename+"\"}");
    }
    @RequestMapping("ReadFile")
    //@ResponseBody
    public String ReadFile(String title, Model model, HttpServletResponse response)throws IOException{
        response.setCharacterEncoding("utf-8");
        model.addAttribute("aaa",ForFlie.readFile(title));
        return "index";
    }
}
