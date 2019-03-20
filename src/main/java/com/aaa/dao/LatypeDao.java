package com.aaa.dao;

import com.aaa.entity.Latype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LatypeDao {
    @Select("select * from latype")
    public List<Latype> query();
}
