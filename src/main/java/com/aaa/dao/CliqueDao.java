package com.aaa.dao;

import com.aaa.entity.Clique;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CliqueDao {
    @Select("select count(*) count from clique where clableid = #{param1} and status = #{param2}")
    public Integer queryCount(Integer clableid,Integer status);
    @Select("select * from clique where clableid = #{param1}")
    public List<Clique> queryByClableid(Integer clableid);
    @Select("select * from clique where userid = #{param1}")
    public List<Clique> queryByUserid(Integer userid);
}
