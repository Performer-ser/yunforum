package com.aaa.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    @Select("select type,content,time,(select username from user_info where userid=fromid) fromid,(select username from user_info where userid=toid) toid from message where toid=#{param1}")
    public List<Map<String,Object>> queryMess(Integer userid);
}
