package com.aaa.dao;

import com.aaa.entity.User_info;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_infoDao {
    @Select("select * from user_info where userid = #{param1}")
    public List<User_info> queryByUserId(Integer userId);
}
