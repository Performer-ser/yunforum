package com.aaa.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    @Select("select type,content,time,(select username from user_info where userid=fromid) fromid,(select username from user_info where userid=toid) toid from message where toid=#{param1}")
    public List<Map<String,Object>> queryMess(Integer userid);
    @Select("select * from attentionuser where  from_userid=#{param1} and  to_userid=#{param2}")
    public List<Map<String,Object>> queryAttUser(Integer from_userid,Integer to_userid);
    @Insert("insert into attentionuser values(#{param1},#{param2},now())")
    public Integer addUser(Integer from_userid,Integer to_userid);
    @Delete("delete from attentionuser where from_userid=#{param1} and to_userid=#{param2}")
    public Integer delUser(Integer from_userid,Integer to_userid);

    @Select("select * from attspecial where  perspid=#{param1} and  userid=#{param2}")
    public List<Map<String,Object>> queryAttSpe(Integer perspid,Integer userid);
    @Insert("insert into attspecial values(#{param1},#{param2},now())")
    public Integer addSpe(Integer perspid,Integer userid);
    @Delete("delete from attspecial where perspid=#{param1} and userid=#{param2}")
    public Integer delSpe(Integer perspid,Integer userid);

    @Select("select * from attentionquestion where  questionid=#{param1} and  userid=#{param2}")
    public List<Map<String,Object>> queryAttQue(Integer questionid,Integer userid);
    @Insert("insert into attentionquestion values(#{param1},#{param2},now())")
    public Integer addQue(Integer questionid,Integer userid);
    @Delete("delete from attentionquestion where questionid=#{param1} and userid=#{param2}")
    public Integer delQue(Integer questionid,Integer userid);

    @Select("select * from attentionlable where  lableid=#{param1} and  userid=#{param2}")
    public List<Map<String,Object>> queryAttLab(Integer lableid,Integer userid);
    @Insert("insert into attentionlable values(#{param1},#{param2},now())")
    public Integer addLab(Integer lableid,Integer userid);
    @Delete("delete from attentionlable where lableid=#{param1} and userid=#{param2}")
    public Integer delLab(Integer lableid,Integer userid);

}
