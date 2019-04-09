package com.aaa.dao;

import com.aaa.entity.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_infoDao {
    @Select("<script>select * from user_info <where><when test=\"userId !=null\"> userid = #{param1}</when><when test=\"search != null and search != ''\">username like CONCAT(CONCAT('%',#{param2}),'%')</when></where></script>")
    public List<Userinfo> queryByUserId(@Param(value = "userId")Integer userId,@Param(value = "search") String search);


}
