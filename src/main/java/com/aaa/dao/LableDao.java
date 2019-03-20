package com.aaa.dao;


import com.aaa.entity.Lable;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LableDao {
    @Select("select l.lableid lableid,l.lablename lablename,l.lablepic lablepic,l.describe `describe`,la.latypename latypeid from lable l,latype la where l.latypeid=la.latypeid ORDER BY l.latypeid")
    public List<Map<String,Object>> query();
    @Delete("delete from lable where lableid=#{param1}")
    public int delete(Integer id);
    @Insert("insert lable(lablepic,lablename,latypeid,`describe`) values(#{lablepic},#{lablename},#{latypeid},#{describe})")
    public int add(Lable lable);
    @Update("update lable set `describe`=#{describe},lablepic=#{lablepic},lablename=#{lablename},latypeid=#{latypeid} where lableid=#{lableid}")
    public int update(Lable lable);
}
