package com.aaa.dao;

import com.aaa.entity.Circletype;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircletypeDao {
    @Select("select * from circletype")
    public List<Circletype> queryAll();
    @Select("select * from circletype where typeid = #{param1}")
    public List<Circletype> queryByTypeid(Integer typeid);

}
