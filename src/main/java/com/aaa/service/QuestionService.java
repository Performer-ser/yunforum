package com.aaa.service;

import com.aaa.dao.QuestionDao;
import com.aaa.entity.Question;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionService {
    @Resource
    QuestionDao qd;
    public int add(Question q){
        return qd.add(q);
    }
}
