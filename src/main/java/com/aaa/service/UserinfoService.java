package com.aaa.service;

import com.aaa.dao.UserinfoDao;
import com.aaa.entity.Userinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserinfoService {
    @Resource
    UserinfoDao ud;
    public List<Userinfo> login(Userinfo user){
        return ud.login(user);
    }
    public Integer register(Userinfo u){
        return ud.register(u);
    }
    public List<Userinfo> queryPage(Integer page,Integer limit){return ud.queryPage(page,limit);};
    public List<Userinfo> queryAll(){return ud.queryAll();};
    public Integer disabled(Integer userid,Integer status){
        return ud.disabled(userid,status);
    }
    public Userinfo queryOne(Integer userid){
        return ud.queryOne(userid);
    }
}
