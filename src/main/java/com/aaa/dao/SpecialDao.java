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

    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,us.userid from special sp left join user_info us on sp.userid=us.userid where lableid=#{param1}")
    public List<Map<String,Object>> query(Integer slableid);
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,us.userid from special sp left join user_info us on sp.userid=us.userid")
    public List<Map<String,Object>> querycenter();
    /**
     * 倒序查询最新内容
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,us.userid from special sp left join user_info us on sp.userid=us.userid order by time desc")
    public List<Map<String,Object>> query1();
    @Select("select * from lable")
    public List<Lable> querySlable();

    /**
     * 根据ID查询文章的详细内容
     * @param specialid
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,la.lablename,per.perspdescribe,per.perspname,per.perspid,us.userid from\n" +
            "special sp left join user_info us on sp.userid=us.userid left join lable la on sp.lableid=la.lableid left join perspecial per on sp.perspid =per.perspid  where sp.specialid =#{param1}")
    public List<Map<String,Object>> queryByid(Integer specialid);

    /**
     * 根据文章id查询专栏id（判断是否关注）
     * @param specialid
     * @return
     */
    @Select("select perspid from special  where specialid=#{param1}")
    public List<Map<String,Object>> queryZLid(Integer specialid);

    /**
     * 根据登录用户id和专栏id查询是否关注
     * @param uid
     * @param zlid
     * @return
     */
    @Select("select * from attspecial where userid=#{param1} and perspid=#{param2}")
    public List<Map<String,Object>> querGuanZhu(Integer uid,Integer zlid);



    @Select("select count(*) dz,s.specialid,s.lableid,s.title,s.content,s.time,us.username,a.status,us.userid\n" +
            "from admire a left join special s on a.type_id = s.specialid left join user_info us on s.userid=us.userid\n" +
            "where  a.status = 1  and a.type =2 group by s.specialid,s.lableid,s.title,s.content,s.userid,s.time,us.username,a.status,us.userid order by dz desc")
    public List<Map<String,Object>> querydz();


    /**
     * 推荐顺序查询分页显示
     * @param offset
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,la.lablename,per.perspname from special sp left join user_info us on sp.userid=us.userid left join lable la on sp.lableid=la.lableid left join perspecial per on sp.perspid=per.perspid limit #{param1},5")
    public List<Map<String,Object>> queryblogs(Integer offset);

    /**
     * 根据点赞数量查询热门内容分页显示
     * @param offset
     * @return
     */
    @Select("select count(*) dz,s.specialid,s.lableid,s.title,s.content,s.time,us.username,a.status,la.lablename,per.perspname\n" +
            " from admire a left join special s on a.type_id = s.specialid left join user_info us on s.userid=us.userid left join \n" +
            " lable la on s.lableid=la.lableid left join perspecial per on s.perspid=per.perspid\n" +
            " where  a.status = 1  and a.type =2 group by s.specialid,s.lableid,s.title,s.content,s.userid,s.time,us.username,a.status,la.lablename,per.perspname  order by dz desc limit #{param1},5")
    public List<Map<String,Object>> queryhottests(Integer offset);

    /**
     * 显示最新更新的文章
     * @param offset
     * @return
     */
    @Select("select sp.specialid,sp.title,sp.content,us.username,sp.time,la.lablename,per.perspname from special sp left join user_info us on sp.userid=us.userid left join lable la on sp.lableid=la.lableid left join perspecial per on sp.perspid=per.perspid order by sp.time desc limit #{param1},5")
    public List<Map<String,Object>> querynewest(Integer offset);
    /**
     * a页面
     */
    @Select("select sp.specialid,sp.title,us.username,la.lablename,la.lableid from special sp left join user_info us on sp.userid= us.userid left join lable la on sp.lableid=la.lableid where sp.specialid <6 order by sp.time desc")
    public List<Map<String,Object>> querya();

    /**
    * 查询个人专栏文章数量，名称，id
     */
    @Select("select p.perspid,p.perspname,count(s.userid) shuliang\n" +
            "from special s,perspecial p\n" +
            "where s.perspid=p.perspid  and p.userid=#{param1}\n" +
            "GROUP BY p.perspid,p.perspname\n")
    public List<Map<String,Object>> queryPS(Integer userid);
    /**
     * 根据个人专栏id查看关注总人数
     */
    @Select("select count(userid) from attspecial where perspid=#{param1}")
    public int queryAtt(Integer perspid);

    /**
     *查看个人全部文章
     */
    @Select("select s.specialid,s.title,p.perspname,s.time\n" +
            "from special s,perspecial p\n" +
            "where s.perspid=p.perspid and s.userid=#{param1}")
    public List<Map<String,Object>> queryAd(Integer userid);
    /**
     * 根据文章编号查看个人文章点赞
     */
    @Select("select count(*) from admire where type=2 and type_id=#{param1}")
    public int queryAA(Integer type_id);
}
