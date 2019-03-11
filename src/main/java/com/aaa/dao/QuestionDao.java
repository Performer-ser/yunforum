package com.aaa.dao;

import com.aaa.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionDao {
    @Insert("insert into question(title,content,time) values(#{title},#{content},now())")
    public int add(Question q);
}
