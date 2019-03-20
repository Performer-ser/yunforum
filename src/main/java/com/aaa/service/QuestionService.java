package com.aaa.service;

import com.aaa.dao.QuestionDao;
import com.aaa.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Resource
    QuestionDao qd;
    public int add(Question q){
        return qd.add(q);
    }
    public List<Map<String,Object>> queryQuestionsByQlable(){
        List<Map<String, Object>> list = qd.queryQuestionsByQlable();
        for (Map<String, Object> q:list){
            //System.out.println(q.get("qlablename"));
            List<String> ls = new ArrayList<String>();
            String s = q.get("lablename").toString();
            Integer ids = Integer.parseInt(q.get("questionid").toString());
            System.out.println("ids:"+ids);
            Integer nums = qd.byAdmire(ids);// 点赞数
            Integer bro = qd.byBrowse(ids);//浏览数量
            Integer rev = qd.byReview(ids);//回答数量

            System.out.println("nums:"+nums);
            q.put("nums",nums);
            q.put("bro",bro);
            q.put("rev",rev);
            String[] str = s.split(",");
            for(int i = 0;i<str.length;i++){
                ls.add(str[i]);
            }
            q.put("lablenames",ls);
            System.out.println(q.get("lablenames"));
        }
        return list;
    }
    public List<Map<String,Object>> quesById(Integer qid){
        return qd.quesById(qid);
    }
    public Integer byAdmire(Integer qid){
        return qd.byAdmire(qid);
    }
    public Integer byReview(Integer qid){
        return qd.byReview(qid);
    }
    public Integer byBrowse(Integer qid){
        return qd.byBrowse(qid);
    }


}
