package com.aaa.dao;


import com.aaa.entity.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface UserinfoDao {
    @Select("select * from user_info where phone=#{phone} and pwd=#{pwd}")
    public List<Userinfo> login(Userinfo user);
    @Insert("insert into user_info(username,phone,pwd) values(#{username},#{phone},#{pwd})")
    public Integer register(Userinfo u);
    @Select("select * from user_info limit #{param1},#{param2}")
    public List<Userinfo> queryPage(Integer page,Integer limit);
    @Select("select * from user_info")
    public List<Userinfo> queryAll();
    @Update("update user_info set userstatus=#{param2} where userid=#{param1}")
    public int disabled(Integer userid,Integer status);
    @Select("select * from user_info where userid=#{param1}")
    public Userinfo queryOne(Integer userid);
}
