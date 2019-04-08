package com.aaa.dao;


import com.aaa.entity.Circle;
import com.aaa.entity.Special;
import com.aaa.entity.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserinfoDao {
    @Select("select * from user_info where phone=#{phone} and pwd=#{pwd}")
    public List<Userinfo> login(Userinfo user);
    @Insert("insert into user_info(username,phone,pwd,head) values(#{username},#{phone},#{pwd},'psb.png')")
    public Integer register(Userinfo u);
    @Select("select * from user_info limit #{param1},#{param2}")
    public List<Userinfo> queryPage(Integer page,Integer limit);
    @Select("select * from user_info")
    public List<Userinfo> queryAll();
    @Update("update user_info set userstatus=#{param2} where userid=#{param1}")
    public int disabled(Integer userid,Integer status);
    @Select("select * from user_info where userid=#{param1}")
    public Userinfo queryOne(Integer userid);
    //查看个人问答
    @Select("select * from question where userid=#{param1}")
    public List<Map<String,Object>> queryQuesAll(Integer userid);
    @Select("select * from question where userid=#{param1} limit #{param2},#{param3}")
    public List<Map<String,Object>> queryQuePage(Integer userid,Integer page,Integer limit);

    @Select("select * from special where userid=#{param1} limit #{param2},#{param3}")
    public List<Special> querySpePage(Integer userid, Integer page, Integer limit);
    @Select("select * from special where userid =#{userid}")
    public List<Special> querySpeAll( Integer userid);


    @Select("select * from circle where userid=#{param1} limit #{param2},#{param3}")
    public List<Circle> queryCirPage(Integer userid, Integer page, Integer limit);
    @Select("select * from circle where userid =#{userid}")
    public List<Circle> queryCirAll( Integer userid);
}
