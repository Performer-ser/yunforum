package com.aaa.service;

import com.aaa.dao.AdmireDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdmireService {
    @Resource
    AdmireDao ad;
    public List<Map<String,Object>> query(Integer type_id, Integer type, Integer userid){
        return ad.query(type_id,type,userid);
    }
    public int update(Integer type_id, Integer type, Integer userid,Integer status){
        return ad.update(type_id,type,userid,status);
    }
    public int add(Integer type_id, Integer type, Integer userid){
        return ad.insert(type_id,type,userid);
    }
    public Integer queryone(Integer type_id, Integer type, Integer userid){
        return ad.queryone(type_id,type,userid);
    }
}
