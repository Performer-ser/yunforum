package com.aaa.service;

import com.aaa.dao.CircletypeDao;
import com.aaa.entity.Circletype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircletypeService {
    @Autowired
    CircletypeDao cd;
    public List<Circletype> queryAll(){
        return cd.queryAll();
    }
    public List<Circletype> queryByTypeid(Integer typeid){
        return cd.queryByTypeid(typeid);
    }

}
