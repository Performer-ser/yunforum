package com.aaa.dao;

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
    @Select("select * from Perspecial")
    public List<Perspecial> queryPerspecial();

    @Select("select latypeid,latypename from latype")
    public List<Latype> queryLatype();

    @Select("select a.latypeid,a.latypename,b.lableid,b.lablename,b.lablepic,b.describe from latype a left join lable b on a.latypeid = b.latypeid where a.latypename = #{param1}")
    public List<Map<String,Object>> queryLableByLatypeid(String lname);

    @Insert("insert into special(lableid,title,content,userid,time,perspid) values(#{lableid},#{title},#{content},#{userid},sysdate(),#{perspid})")
    public void addspe(Special spe);
}
