package com.aaa.service;


import com.aaa.dao.LatypeDao;
import com.aaa.dao.SpecialDao;
import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class LatypeService {
    @Resource
    LatypeDao ld;
    @Resource
    SpecialDao sd;

    public List<Latype> query(){
        return ld.query();
    }

    public List<Latype> querylatype(){
       return ld.querylatype();
    }
    public List<Lable> querylable(){
        return ld.querylable();
    }
    public List<Lable> querylables(){
        return ld.querylables();
    }
    public List<Map<String, Object>> queryxg(Integer latypeid){
        return ld.queryxg(latypeid);
    }

    /**
     * 根据标签ID查询内容
     * @param lableid
     * @return
     */
    public List<Map<String,Object>> queryBysid(Integer lableid){
        List<Map<String,Object>> list = ld.queryBysid(lableid);
        return list;
    }
    /*public List<Map<String,Object>> queryBysid(String lablename){
        List<Map<String,Object>> list = ld.queryBysid(lablename);
        return list;
    }*/

    public List<Map<String,Object>> queryByid(Integer specialid){
        List<Map<String,Object>> list = sd.queryByid(specialid);
        return list;
    }
    public List<Map<String,Object>> querya(){
        return sd.querya();
    }


    public int add(Latype latype){
        return ld.add(latype);
    }
    public int update(Latype latype){
        return ld.update(latype);
    }
    public int delete(Integer id){
        return ld.delete(id);
    }
    /*public int addattentiongz(int attentiongzid,int userid,int lableid){
        return ld.addattentiongz(attentiongzid,userid,lableid);
    }*/
}
