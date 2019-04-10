package com.aaa.service;

import com.aaa.dao.SpecialDao;

import com.aaa.entity.Lable;
import com.aaa.entity.Slable;
import com.aaa.entity.Special;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.rmi.MarshalledObject;
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

    /**
     * 根据文章id查询专栏id（判断是否关注）
     * @param specialid
     * @return
     */
    public List<Map<String,Object>> queryZLid(Integer specialid){
        return sd.queryZLid(specialid);
    }

    /**
     * 根据登录用户id和专栏id查询是否关注
     * @param uid
     * @param zlid
     * @return
     */
    public List<Map<String,Object>> querGuanZhu(Integer uid,Integer zlid){
        return sd.querGuanZhu(uid,zlid);
    }

    public List<Map<String,Object>> queryBiaoqian(Integer uid,Integer lableid){
        return sd.queryBiaoqian(uid,lableid);
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
