package com.aaa.service;

import com.aaa.dao.CircleDao;
import com.aaa.entity.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CircleService {
    @Autowired
    CircleDao cd;
    public Map<String,Object> query(Integer offict, Integer pagesize){
        Map<String, Object> map = new HashMap<String, Object>();
        int a = (offict - 1)* pagesize;
       /* List<Circle> query = cd.query(a, pagesize);*/
        List<Circle> query = cd.query(a,pagesize);
        int querys = cd.queryAll().size();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", querys);
        map.put("data", query);
        return map;
    }
    public List<Circle> queryAll(){
        return cd.queryAll();
    }
    public List<Circle> diffTime(){
        return cd.diffTime();
    }
    public List<Circle> queryByClableid(Integer clableid){
        return cd.queryByClableid(clableid);
    }
    public Integer querycountByClableid(Integer clableid){
        return cd.querycountByClableid(clableid);
    }
}
