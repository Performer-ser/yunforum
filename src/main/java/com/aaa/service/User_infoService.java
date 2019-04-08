package com.aaa.service;

import com.aaa.dao.User_infoDao;
import com.aaa.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_infoService {
    @Autowired
    User_infoDao ud;
    public List<Userinfo> queryByUserId(Integer userId,String search){
        return  ud.queryByUserId(userId,search);
    }
}
