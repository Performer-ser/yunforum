package com.aaa.dao;

import com.aaa.entity.Slable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SlableDao {
    /*@Select("select * from slable")
    public List<Slable> querySlable();*/

   /* @Select("select spe.specialid specialids,spe.title titles,spe.content contents,spe.time times,ui.username usernames from special spe inner join slable sla on spe.slableid =sla.slableid inner join user_info ui on ui.userid=spe.userid where sla.slableid=#{slableid}")
    public List<Map<String,Object>> queryByID1(Integer slableid);*/
}
