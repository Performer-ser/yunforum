package com.aaa.dao;

import com.aaa.entity.Question;
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
    @Select("select lableid from attentionlable where userid=#{param1}")
    public List<Integer> querylaid(Integer userid);
    @Insert("insert into attentionlable values(#{param1},#{param2},now())")
    public Integer addLab(Integer lableid,Integer userid);
    @Delete("delete from attentionlable where lableid=#{param1} and userid=#{param2}")
    public Integer delLab(Integer lableid,Integer userid);

    @Select("select count(*) from attentionuser where from_userid=#{param1}")
    public Integer fromme(Integer from_userid);
    @Select("select count(*) from attentionuser where to_userid=#{param1}")
    public Integer tome(Integer to_userid);

    @Select("select count(*) from attentionlable where lableid=#{param1}")
    public Integer lableAll(Integer lableid);
    @Select("select to_userid from attentionuser where from_userid = #{param1}")
    public List<Integer> queryto(Integer from);
    @Select("select perspid from attspecial where userid=#{param1}")
    public List<Integer> myspe(Integer userid);
    @Select("select * from perspecial where perspid=#{param1}")
    public Map<String,Object> perspeAll(Integer perspid);
    @Select("select count(*) from special where perspid=#{param1}")
    public Integer  countSpe(Integer perspid);
    @Select("select questionid from attentionquestion where userid=#{param1}")
    public List<Integer> userQues(Integer userid);
    @Select("select * from question where questionid=#{param1}")
    public Question selectQues(Integer questionid);
    @Select("select count(*) from review where composeid=#{param1} and type=1")
    public Integer reQues(Integer quesionid);
    @Select("select count(*) from attentionquestion where questionid=#{param1}")
    public Integer guanQue(Integer questionid);
    @Select("select perspdescribe,perspname,userid from  perspecial where perspid=#{param1}")
    public Map<String,Object> QueDescribe(Integer perspid);
    @Select("select s.specialid,s.title,s.time,u.userid,u.username,u.head\n" +
            "from special s,user_info u\n" +
            "where s.userid=u.userid and s.perspid=#{param1}")
    public List<Map<String,Object>> userQue(Integer perspid);

    @Select("select * from question where title like concat('%',#{param1},'%')")
    public List<Question> queryzi(String zi);
}
