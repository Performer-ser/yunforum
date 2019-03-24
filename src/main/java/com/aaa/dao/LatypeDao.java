package com.aaa.dao;

import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface LatypeDao {
    @Select("select * from latype")
    public List<Latype> query();

    @Select("select * from latype")
    public List<Latype> querylatype();
    @Select("select * from lable")
    public List<Lable> querylable();
}
