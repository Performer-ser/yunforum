package com.aaa.dao;


import com.aaa.entity.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserinfoDao {
    @Select("select * from user_info where phone=#{phone} and pwd=#{pwd}")
    public List<Userinfo> login(Userinfo user);
    @Insert("insert into user_info(username,phone,pwd) values(#{username},#{phone},#{pwd})")
    public Integer register(Userinfo u);
}
