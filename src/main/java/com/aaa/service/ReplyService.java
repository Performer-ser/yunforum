package com.aaa.service;

import com.aaa.dao.ReplyDao;
import com.aaa.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    ReplyDao rd;
    public List<Reply> queryByReviewid(Integer reviewid){
        return  rd.queryByReviewid(reviewid);
    }
}
