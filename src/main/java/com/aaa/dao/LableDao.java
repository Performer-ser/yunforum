package com.aaa.dao;

import com.aaa.entity.Lable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface LableDao {
    @Select("select * from lable")
    public List<Lable> query();
}
