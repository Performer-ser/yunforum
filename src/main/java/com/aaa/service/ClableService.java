package com.aaa.service;

import com.aaa.dao.ClableDao;
import com.aaa.entity.Clable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClableService {

    @Autowired
    ClableDao cd;
    public Map<String,Object> query(Integer offict,Integer pagesize){
        Map<String, Object> map = new HashMap<String, Object>();
        int a = (offict - 1)* pagesize;
        List<Clable> query = cd.query(a, pagesize);
        int querys = cd.queryAll().size();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", querys);
        map.put("data", query);
        return map;
    }
    public List<Clable> queryByClableid(Integer clableid){
        return cd.queryByClableid(clableid);
    }
    public List<Clable> queryAll(){
        return  cd.queryAll();
    }
    public List<Clable> queryByTypeid(Integer typeid){
        return cd.queryByTypeid(typeid);
    }
    public  List<Clable> queryByClablename(String clablename){
        return cd.queryByClablename(clablename);
    }

}
