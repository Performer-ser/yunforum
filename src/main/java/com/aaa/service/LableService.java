package com.aaa.service;

import com.aaa.dao.LableDao;
import com.aaa.entity.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LableService {
    @Resource
    LableDao ld;
    public List<Map<String,Object>> query(){
        return ld.query();
    }
    public int delete(Integer id){
        return ld.delete(id);
    }
    public int add(Lable lable){
        return ld.add(lable);
    }
    public int update(Lable lable){
        return ld.update(lable);
    }
}
