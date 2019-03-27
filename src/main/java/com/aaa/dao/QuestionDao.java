package com.aaa.dao;

import com.aaa.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface QuestionDao {
    @Insert("insert into question(title,content,time) values(#{title},#{content},now())")
    public int add(Question q);

    @Select("select u.username,q.questionid,q.title,q.content,q.userid,q.time,GROUP_CONCAT(b.lablename) lablename from user_info u left join question q on u.userid = q.userid left join lable b on FIND_IN_SET(b.lableid,q.lableid) group by u.username,q.questionid,q.title,q.content,q.userid,q.time order by time desc")
    public List<Map<String,Object>> queryQuestionsByQlable();

    @Select("select u.username,q.questionid,q.title,q.content,q.userid,q.time,GROUP_CONCAT(b.lablename) lablename from user_info u left join question q on u.userid = q.userid left join lable b on FIND_IN_SET(b.lableid,q.lableid) where q.questionid=#{param1} group by u.username,q.questionid,q.title,q.content,q.userid,q.time order by time desc")
    public List<Map<String,Object>> quesById(Integer qid);

    //查询点赞表
    @Select("select count(*) dizan from admire a where a.type = 1 and a.status = 1 and type_id=#{param1}")
    public Integer byAdmire(Integer qid);
    //查看回答表
    @Select("select count(*) huida from review r where r.type = 1 and r.composeid = #{param1}")
    public Integer byReview(Integer qid);
    //查看浏览表
    @Select("select count(*) from browse b where b.type =1 and b.composeid = #{parma1}")
    public Integer byBrowse(Integer qid);
    @Select("select title from question where questionid=#{param1}")
    public Map<String,Object> queryTitle(Integer questionid);


}
