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

    @Select("select questionid,title,content,userid,time,GROUP_CONCAT(b.lablename) lablename from question a left join lable b on FIND_IN_SET(b.lableid,a.lableid) group by questionid,title,content,userid,time order by time desc")
    public List<Map<String,Object>> queryQuestionsByQlable();

    @Select("select q.*,u.* from question q inner join user_info u on q.userid = u.userid where q.questionid = #{qid}")
    public List<Map<String,Object>> quesById(Integer qid);

    //查询点赞表
    @Select("select count(*) dizan from admire a where a.type = 1 and a.status = 1 and type_id=#{param1}")
    public Integer byAdmire(Integer qid);
    @Select("select count(*) huida from review r where r.type = 1 and r.composeid = #{param1}")
    public Integer byReview(Integer qid);
    @Select("select count(*) from browse b where b.type =1 and b.composeid = #{parma1}")
    public Integer byBrowse(Integer qid);



}
