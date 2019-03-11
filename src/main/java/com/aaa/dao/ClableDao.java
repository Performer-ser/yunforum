package com.aaa.dao;

import com.aaa.entity.Clable;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClableDao {
    @Select("select * from clable limit #{param1},#{param2}")
    public List<Clable> query(Integer offict, Integer pagesize);
    @Select("select * from clable")
    public List<Clable> queryAll();
    @Select("select * from clable where clableid = #{param1}")
    public List<Clable> queryByClableid(Integer clableid);
    @Select("select * from clable where typeid = #{param1}")
    public List<Clable> queryByTypeid(Integer typeid);
    @Select("select * from clable where clablename = #{param1}")
    public  List<Clable> queryByClablename(String clablename);
}
