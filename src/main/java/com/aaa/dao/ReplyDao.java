package com.aaa.dao;

import com.aaa.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyDao {
    @Select("select * from reply where reviewid = #{param1}")
    public List<Reply> queryByReviewid(Integer reviewid);
    @Insert("insert into reply values (null,#{reviewid},#{content},#{from_userid},#{to_userid},SYSDATE())")
    public Integer addByReply(Reply r);
}
