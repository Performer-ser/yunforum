package com.aaa.service;

import com.aaa.dao.SpecialDao;

import com.aaa.entity.Lable;
import com.aaa.entity.Slable;
import com.aaa.entity.Special;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecialService {
    @Resource
    SpecialDao sd;


    public List<Map<String,Object>> query(Integer slableid){
       return sd.query(slableid);
    }

    public List<Map<String,Object>> querycenter(){
        return sd.querycenter();
    }
    public List<Map<String,Object>> query1(){
        return sd.query1();
    }

   /* public Map<String,Object> centers (Integer )*/

    public List<Lable> querySlable(){
        return sd.querySlable();
    }

    public List<Map<String,Object>> queryByid(Integer specialid){
        List<Map<String,Object>> list = sd.queryByid(specialid);
        return list;
    }

    public Map<String,Object> centers(Integer slableid){
        Map<String, Object> list = new HashMap<String, Object>();
        list.put("list",sd.querySlable());
        list.put("list",sd.query(slableid));
        System.out.println(list);
        return list;
    }
    public List<Map<String,Object>> querydz(){
        return sd.querydz();
    }

    public List<Map<String,Object>> queryblogs(Integer pageNum){
        Integer offset =  (pageNum - 1) * 5;
        return sd.queryblogs(offset);
    }

    public List<Map<String,Object>> queryhottests(Integer pageNum){
        Integer offset =  (pageNum - 1) * 5;
        return sd.queryhottests(offset);
    }

    public List<Map<String,Object>> querynewest(Integer pageNum){
        Integer offset =  (pageNum - 1) * 5;
        return sd.querynewest(offset);
    }
    public List<Map<String,Object>> querya(){
        return sd.querya();
    }

    public List<Map<String,Object>> queryPS(Integer userid){return sd.queryPS(userid);}
    public int queryAtt(Integer perspid){return sd.queryAtt(perspid);}
    public List<Map<String,Object>> queryAd(Integer userid){return sd.queryAd(userid);}
    public int queryAA(Integer type_id){return sd.queryAA(type_id);}
}
