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
    //查询问答根据时间倒序
    @Select("select u.username,q.questionid,q.title,q.content,q.userid,q.time,GROUP_CONCAT(b.lablename) lablename from user_info u left join question q on u.userid = q.userid left join lable b on FIND_IN_SET(b.lableid,q.lableid) group by u.username,q.questionid,q.title,q.content,q.userid,q.time order by time desc limit #{param1},15")
    public List<Map<String,Object>> queryQuestionsByQlable(Integer offset);
    //根据问答的id查询问答详情
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
    public List<Question> queryUser(Integer userid,Integer page);
    //查看个人全部问题
    @Select("select title,reviewid,time from question where userid=#{param1}")
    public List<Map<String,Object>> queryUserAll(Integer userid);
    //查看所有标签类型
    @Select("select latypeid,latypename from latype")
    public List<Latype> queryLatype();
    ////查询标签及标签类型根据标签类型id
    @Select("select *from lable")
    public List<Lable> queryLableByLatypeid();
    //查看标签详情根据标签名称得到标签id传到详情页面
    @Select("select distinct lableid from lable where lablename =#{param1} limit 0,1")
    public int queryLableByLablename(String lablename);
    //添加浏览数据
    @Insert("insert into browse(browseid,type,composeid,userid,browsetime) values(#{browseid},1,#{composeid},'1',SYSDATE())")
    public Integer addBrowse(Browse browse);
    //查询问答的关注数量
    @Select("select count(distinct userid) gz from attentionquestion where questionid=#{param1} ")
    public Integer byAttentionQuestion(Integer questionid);
    //添加问答数据
    @Insert("INSERT INTO question(lableid,title,content,userid,time,reviewid) VALUES (#{lableid},#{title},#{content},#{userid},sysdate(),#{reviewid});")
    public void addspe(Question que);
    @Select("select * from lable where lablename=#{lablename}")
    public List<Lable> querylablename(String lablename);
    //添加回复
    @Insert("insert into review VALUES(null,#{composeid},1,#{content},#{userid},SYSDATE())")
    public Integer addByReview(Review r);
    //查看个人全部回答
    @Select("select * from review where userid=#{param1} and type=1")
    public List<Review> queryAllAnswer(Integer userid);
}
