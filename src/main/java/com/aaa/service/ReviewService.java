package com.aaa.service;

import com.aaa.dao.ReviewDao;
import com.aaa.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDao rd;
    public List<Review> queryByComposeid(Integer composeid){
        return  rd.queryByComposeid(composeid);
    }
}
