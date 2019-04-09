package com.aaa.service;

import com.aaa.dao.CliqueDao;
import com.aaa.entity.Clique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CliqueService {
    @Autowired
    CliqueDao cd;
    public Integer queryCount(Integer clableid,Integer status){
        return cd.queryCount(clableid,status);
    }
    public List<Clique> queryByClableid(Integer clableid){
        return cd.queryByClableid(clableid);
    }
    public List<Clique> queryByUserid(Integer userid){
        return cd.queryByUserid(userid);
    }
    public Integer queryone(Integer userid,Integer clableid){
        return cd.queryone(userid,clableid);
    }
    public int add(Integer type_id, Integer userid){
        return cd.insert(type_id,userid);
    }
    public int update(Integer type_id, Integer userid,Integer status){
        return cd.update(type_id,userid,status);
    }
}
