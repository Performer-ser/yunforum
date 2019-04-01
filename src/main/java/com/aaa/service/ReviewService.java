package com.aaa.service;

import com.aaa.dao.ReviewDao;
import com.aaa.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    @Autowired
    ReviewDao rd;
    public List<Review> queryByComposeid(Integer composeid){
        return  rd.queryByComposeid(composeid);
    }
    public List<Review> queryByUserid(Integer userid){
        return  rd.queryByUserid(userid);
    }
    public List<Map<String,Object>> queryOneAll(Integer userid,Integer page){return rd.queryOneAll(userid,page);}
    public List<Map<String,Object>> queryUserAll(Integer userid){return rd.queryUserAll(userid);}
    public Integer addByReview(Review r){
        return  rd.addByReview(r);
    }
}
