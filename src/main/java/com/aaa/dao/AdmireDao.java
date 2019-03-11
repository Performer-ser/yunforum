package com.aaa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdmireDao {
    @Select("select count(*) count,(select count(*) from admire where userid=#{userid} and type=#{type} and type_id=#{type_id} and status=1) status from admire where type=#{type} and type_id=#{type_id} and status=1")
    public List<Map<String,Object>> query(Integer type_id, Integer type, Integer userid);

}
