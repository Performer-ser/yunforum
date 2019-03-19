package com.aaa.service;

import com.aaa.dao.SlableDao;
import com.aaa.entity.Slable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlableService {
    /*@Resource
    SlableDao sd;
    public List<Slable> querySlable(){
        return sd.querySlable();
    }*/
    /*public Map<String,Object> query2(Integer slableid){
        Map<String, Object> maps = new HashMap<String,Object>();
        *//*list.put("list",sd.queryByid(slableid));*//*
        maps.put("list",sd.querySlable());
        maps.put("lists",sd.queryByID1(slableid));
        System.out.println(maps);
        return maps;
    }*/
}
