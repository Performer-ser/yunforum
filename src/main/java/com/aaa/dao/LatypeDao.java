package com.aaa.dao;

import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface LatypeDao {
    @Select("select * from latype")
    public List<Latype> query();

    @Select("select * from latype")
    public List<Latype> querylatype();
    @Select("select * from lable")
    public List<Lable> querylable();
    @Insert("insert latype (latypename) values(#{latypename})")
    public int add(Latype latype);
    @Update("update latype set latypename=#{latypename} where latypeid=#{latypeid}")
    public int update(Latype latype);
    @Delete("delete from latype where latypeid=#{param1}")
    public int delete(Integer id);
}
