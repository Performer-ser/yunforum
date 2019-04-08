package com.aaa.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdmireDao {
    @Select("select count(*) count,(select count(*) from admire where userid=#{userid} and type=#{type} and type_id=#{type_id} and status=1) status from admire where type=#{type} and type_id=#{type_id} and status=1")
    public List<Map<String,Object>> query(Integer type_id, Integer type, Integer userid);
    @Update("update admire set status=#{status} where userid=#{userid} and type_id=#{type_id} and type=#{type}")
    public int update(Integer type_id, Integer type, Integer userid,Integer status);
    @Insert("insert into admire(type_id,type,userid,status,time) values(#{type_id},#{type},#{userid},1,now())")
    public int insert(Integer type_id, Integer type, Integer userid);
    @Select("select status from admire where userid=#{userid} and type=#{type} and type_id=#{type_id}")
    public Integer queryone(Integer type_id, Integer type, Integer userid);
    @Select("select count(*) count from admire where type=#{param2} and type_id=#{param1} and status=1")
    public Integer queryCount(Integer type_id, Integer type);

}
