package com.aaa.service;

import com.aaa.dao.QuestionDao;

import com.aaa.entity.Question;
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
            String[] str = s.split(",");
            for(int i = 0;i<str.length;i++){
                ls.add(str[i]);
            }
            q.put("lablenames",ls);
            System.out.println(q.get("lablenames"));
        }
        System.out.println(list);
        return list;
    }
    public List<Map<String,Object>> quesById(Integer qid){
        return qd.quesById(qid);
    }

}
