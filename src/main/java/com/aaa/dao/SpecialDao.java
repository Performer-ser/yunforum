package com.aaa.dao;

import com.aaa.entity.Lable;
import com.aaa.entity.Slable;

import com.aaa.entity.Special;
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

    /**
     * 根据ID查询文章的详细内容
     * @param specialid
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time from special sp left join user_info us on sp.userid=us.userid where sp.specialid =#{specialid}")
    public List<Map<String,Object>> queryByid(Integer specialid);

    @Select("select count(*) dz,s.specialid,s.lableid,s.title,s.content,s.time,us.username,a.status\n" +
            "from admire a left join special s on a.type_id = s.specialid left join user_info us on s.userid=us.userid\n" +
            "where  a.status = 1  and a.type =2 group by s.specialid,s.lableid,s.title,s.content,s.userid,s.time,us.username,a.status order by dz desc")
    public List<Map<String,Object>> querydz();


    /**
     * 推荐顺序查询分页显示
     * @param offset
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,la.lablename from special sp left join user_info us on sp.userid=us.userid left join lable la on sp.lableid=la.lableid limit #{param1},5")
    public List<Map<String,Object>> queryblogs(Integer offset);

    /**
     * 根据点赞数量查询热门内容分页显示
     * @param offset
     * @return
     */
    @Select("select count(*) dz,s.specialid,s.lableid,s.title,s.content,s.time,us.username,a.status,la.lablename\n" +
            " from admire a left join special s on a.type_id = s.specialid left join user_info us on s.userid=us.userid left join \n" +
            " lable la on s.lableid=la.lableid\n" +
            " where  a.status = 1  and a.type =2 group by s.specialid,s.lableid,s.title,s.content,s.userid,s.time,us.username,a.status,la.lablename  order by dz desc limit #{param1},5")
    public List<Map<String,Object>> queryhottests(Integer offset);

    /**
     * 显示最新更新的文章
     * @param offset
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,la.lablename from special sp left join user_info us on sp.userid=us.userid left join lable la on sp.lableid=la.lableid order by sp.time desc limit #{param1},5")
    public List<Map<String,Object>> querynewest(Integer offset);


}
