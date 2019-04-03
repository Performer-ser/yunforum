package com.aaa.dao;

import com.aaa.entity.Letter;
import com.aaa.entity.Userinfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface LetterDao {
    @Select("select distinct fromid from letter where toid=#{param1}")
    public List<Integer> queryFromid(Integer toid);
    @Select("select * from letter where fromid=#{param1} order by time desc limit 1")
    public Letter queryLe(Integer fromid);
    @Select("select * from user_info where userid=#{param}")
    public Userinfo queryuserinfo(Integer userid);
    @Select("select count(*) from letter where fromid=#{param1} and toid=#{param2} and status=0")
    public Integer queryStatus(Integer fromid, Integer toid);
}
