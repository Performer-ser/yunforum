package com.aaa.dao;

import com.aaa.entity.Review;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReviewDao {
    @Select("select * from review where composeid = #{param1} ")
    public List<Review> queryByComposeid(Integer composeid);
    @Select("select * from review where userid = #{param1}")
    public List<Review> queryByUserid(Integer userid);
    //查看个人的所有回答
    @Select("select reviewid,time,composeid from review where type=1 and userid=#{param1}")
    public List<Map<String,Object>> queryOneAll(Integer userid);

}
