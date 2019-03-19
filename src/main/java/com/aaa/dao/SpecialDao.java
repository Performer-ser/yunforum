package com.aaa.dao;

import com.aaa.entity.Lable;
import com.aaa.entity.Slable;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SpecialDao {

    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time from special sp left join user_info us on sp.userid=us.userid where lableid=#{param1}")
    public List<Map<String,Object>> query(Integer slableid);
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time from special sp left join user_info us on sp.userid=us.userid")
    public List<Map<String,Object>> querycenter();
    /**
     * 倒序查询最新内容
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time from special sp left join user_info us on sp.userid=us.userid order by time desc")
    public List<Map<String,Object>> query1();
    @Select("select * from lable")
    public List<Lable> querySlable();
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time from special sp left join user_info us on sp.userid=us.userid where sp.specialid =#{specialid}")
    public List<Map<String,Object>> queryByid(Integer specialid);
    /*@Select("select spe.specialid specialids,spe.title titles,spe.content contents,spe.time times,ui.username usernames from special spe inner join slable sla on spe.slableid =sla.slableid inner join user_info ui on ui.userid=spe.userid where sla.slableid=#{slableid}")
    public List<Map<String,Object>> query2(Integer slableid);*/


    /*public List<Map<String,Object>> queryByids(Integer )*/
    /*public List<Map<String,Object>> query2(Integer slableid);*/

}
