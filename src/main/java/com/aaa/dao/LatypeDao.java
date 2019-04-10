package com.aaa.dao;

import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface LatypeDao {
    @Select("select * from latype")
    public List<Latype> query();

    @Select("select * from latype")
    public List<Latype> querylatype();
    @Select("select * from lable")
    public List<Lable> querylable();
    @Select("select * from lable where lableid <19")
    public List<Lable> querylables();

    /**
     * 根据标签ID查询出于与此标签有关的内容
     */
    @Select("select la.lableid,la.latypeid,la.lablename,la.describe,sp.specialid,sp.lableid slableid,sp.title,sp.content,us.userid,us.username,sp.time from lable la left join special sp" +
            " on la.lableid= sp.lableid left join user_info us on sp.userid= us.userid where la.lableid =#{param1}")
    public List<Map<String,Object>> queryBysid(Integer lableid);


    @Insert("insert latype (latypename) values(#{latypename})")
    public int add(Latype latype);
    @Update("update latype set latypename=#{latypename} where latypeid=#{latypeid}")
    public int update(Latype latype);
    @Delete("delete from latype where latypeid=#{param1}")
    public int delete(Integer id);
    /**
     * a页面
     */
    @Select("select sp.title,us.username,la.lablename from special sp left join user_info us on sp.userid= us.userid left join lable la on sp.lableid=la.lableid where sp.specialid <6 order by sp.time desc")
    public List<Map<String,Object>> querya();
    @Select("select lt.latypename,la.lablename,la.lableid from latype lt left join lable la on lt.latypeid=la.latypeid where la.latypeid=#{param1}")
    public List<Map<String,Object>> queryxg(Integer  latypeid);
    /**
     * t页面的关注
     *//*
    @Insert("insert into attentiongz(attentiongzid,userid,lableid) values(#{attentiongzid},#{userid},#{lableid})")
    public int addattentiongz(int attentiongzid,int userid,int lableid);
    *//**
     * t页面取消关注
     *//*
    @Delete("delete from attentiongz where attentiongzid=#{param1}")
    public int delattentiongz(Integer attentiongzid);*/

}
