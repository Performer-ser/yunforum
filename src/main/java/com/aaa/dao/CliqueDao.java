package com.aaa.dao;

import com.aaa.entity.Clique;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Select("select status from clique where userid=#{userid} and clableid = #{clableid}")
    public Integer queryone(Integer userid,Integer clableid);
    @Update("update clique set status=#{param3} where userid=#{param2} and clableid=#{param1}")
    public int update(Integer type_id, Integer userid,Integer status);
    @Insert("insert into clique VALUES(null,#{param1},#{param2},SYSDATE(),1);")
    public int insert(Integer clableid, Integer userid);
}
