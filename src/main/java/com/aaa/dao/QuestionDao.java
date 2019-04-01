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

    @Select("select u.username,q.questionid,q.title,q.content,q.userid,q.time,GROUP_CONCAT(b.lablename) lablename from user_info u left join question q on u.userid = q.userid left join lable b on FIND_IN_SET(b.lableid,q.lableid) group by u.username,q.questionid,q.title,q.content,q.userid,q.time order by time desc limit #{param1},15")
    public List<Map<String,Object>> queryQuestionsByQlable(Integer offset);

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
    @Select("select title,reviewid from question where questionid=#{param1}")
    public Map<String,Object> queryTitle(Integer questionid);
    //查看回答表及回答用户根据问答的id
    @Select("select q.questionid,r.content,r.time,u.username,u.head from question q left join review r on q.questionid = r.composeid left join user_info u on r.userid = u.userid where r.type = 1 and questionid = #{param1}")
    public List<Map<String,Object>> queryReviewByQuestionid(Integer qid);
    //分页查看个人问题
    @Select("select questionid,title,reviewid,time from question where userid=#{param1} limit #{param2},20")
    public List<Map<String,Object>> queryUser(Integer userid,Integer page);
    //查看个人全部问题
    @Select("select title,reviewid,time from question where userid=#{param1}")
    public List<Map<String,Object>> queryUserAll(Integer userid);
    //查看所有标签类型
    @Select("select latypeid,latypename from latype")
    public List<Lable> queryLatype();
    ////查询标签及标签类型根据标签类型id
    @Select("select a.latypeid,a.latypename,b.lableid,b.lablename,b.lablepic,b.describe from latype a left join lable b on a.latypeid = b.latypeid where a.latypeid = #{param1}")
    public List<Map<String,Object>> queryLableByLatypeid(Integer lid);
}
