package com.aaa.service;

import com.aaa.dao.CircleDao;
import com.aaa.entity.Circle;
import com.aaa.entity.Clable;
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
    public List<Circle> queryByUserid(Integer userid){
        return cd.queryByUserid(userid);
    }
    public Integer add(Circle c){
        return cd.add(c);
    }
    public List<Circle> queryByCircleid(Integer circleid){
        return cd.queryByCircleid(circleid);
    }
    public List<Circle> diffTimeBy(Integer[] clableids){
        return cd.diffTimeBy(clableids);
    }
    public List<Circle> queryByClableid2(Integer clableid,Integer page){
        return cd.queryByClableid2(clableid,page);
    }
    public Integer addcl(Clable c){
        return  cd.addcl(c);
    }
    public int update(Clable c){
        return  cd.update(c);
    }
    public int delete(Integer id){
        return cd.delete(id);
    }
}
