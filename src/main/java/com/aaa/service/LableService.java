package com.aaa.service;

import com.aaa.dao.LableDao;
import com.aaa.entity.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LableService {
    @Resource
    LableDao ld;
    public List<Lable> query(){
        return ld.query();
    }
}
