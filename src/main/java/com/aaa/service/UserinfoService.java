package com.aaa.service;

import com.aaa.dao.UserinfoDao;
import com.aaa.entity.Circle;
import com.aaa.entity.Special;
import com.aaa.entity.Userinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public List<Userinfo> queryPage(Integer page,Integer limit){return ud.queryPage(page,limit);}
    public List<Userinfo> queryAll(){return ud.queryAll();};
    public Integer disabled(Integer userid,Integer status){
        return ud.disabled(userid,status);
    }
    public Userinfo queryOne(Integer userid){
        return ud.queryOne(userid);
    }
    public List<Map<String,Object>> queryQuesAll(Integer userid){ return ud.queryQuesAll(userid);}
    public List<Map<String,Object>> queryQuesPage(Integer userid,Integer page,Integer limit){ return ud.queryQuePage(userid,page,limit);}
    public List<Special> querySpePage(Integer userid, Integer page, Integer limit){return ud.querySpePage(userid,page,limit);}
    public List<Special> querySpeAll(Integer userid){return ud.querySpeAll(userid);}
    public List<Circle> queryCirPage(Integer userid, Integer page, Integer limit){return ud.queryCirPage(userid,page,limit);}
    public List<Circle> queryCirAll(Integer userid){return ud.queryCirAll(userid);}
    public Integer userup(Userinfo u){return ud.userup(u);}
}
