package com.aaa.service;


import com.aaa.dao.LatypeDao;
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

    public List<Latype> query(){
        return ld.query();
    }

    public List<Latype> querylatype(){
       return ld.querylatype();
    }
    public List<Lable> querylable(){
        return ld.querylable();
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


    public int add(Latype latype){
        return ld.add(latype);
    }
    public int update(Latype latype){
        return ld.update(latype);
    }
    public int delete(Integer id){
        return ld.delete(id);
    }
}
