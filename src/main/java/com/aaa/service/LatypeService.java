package com.aaa.service;


import com.aaa.dao.LatypeDao;
import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LatypeService {
    @Resource
    LatypeDao ld;

    public List<Latype> query(){
        return ld.query();
    }

    public List<Latype> querylatype(){
       return ld.querylatype();
    }
    public List<Lable> querylable(){
        return ld.querylable();
    }
}
