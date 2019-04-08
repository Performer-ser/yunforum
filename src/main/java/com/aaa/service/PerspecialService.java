package com.aaa.service;

import com.aaa.dao.PerspecialDao;
import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import com.aaa.entity.Perspecial;
import com.aaa.entity.Special;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PerspecialService {
    @Resource
    PerspecialDao pd;
    public void add(Perspecial per){
        pd.add(per);
    }
    public List<Perspecial> queryPerspecial(){
        return pd.queryPerspecial();
    }

    public List<Latype> queryLatype(){
        return pd.queryLatype();
    }

    public List<Lable> queryLable(){
        return pd.queryLable();
    }
    public void addspe(Special spe){
        pd.addspe(spe);
    }
    public List<Lable> querylablename(String lablename){
        return pd.querylablename(lablename);
    }
}
