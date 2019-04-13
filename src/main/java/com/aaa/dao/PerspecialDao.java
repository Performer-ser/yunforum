package com.aaa.dao;

import com.aaa.entity.Lable;
import com.aaa.entity.Latype;
import com.aaa.entity.Perspecial;
import com.aaa.entity.Special;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PerspecialDao {
    @Insert("insert into perspecial(perspid,perspname,perspdescribe,userid,time) values" +
            "(#{perspid},#{perspname},#{perspdescribe},#{userid},sysdate())")
    public void add(Perspecial per);
    @Select("select * from Perspecial where userid=#{param1}")
    public List<Perspecial> queryPerspecial(Integer userid);

    @Select("select * from latype")
    public List<Latype> queryLatype();

    @Select("select * from lable ")
    public List<Lable> queryLable();

    @Insert("insert into special(lableid,title,content,userid,time,perspid) values(#{lableid},#{title},#{content},#{userid},sysdate(),#{perspid})")
    public void addspe(Special spe);
    @Select("select * from lable where lablename=#{lablename}")
    public List<Lable> querylablename(String lablename);
}
