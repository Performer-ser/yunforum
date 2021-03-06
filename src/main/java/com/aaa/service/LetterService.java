package com.aaa.service;

import com.aaa.dao.LetterDao;
import com.aaa.entity.Letter;
import com.aaa.entity.Userinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LetterService {
    @Resource
    LetterDao ld;
    public List<Integer> queryFromid(Integer toid){return ld.queryFromid(toid);}
    public Letter queryLe(Integer fromid){return ld.queryLe(fromid);}
    public Userinfo queryuserinfo(Integer userid){return ld.queryuserinfo(userid);}
    public Integer queryStatus(Integer fromid,Integer toid){return ld.queryStatus(fromid,toid);}
    public List<Map<String,Object>> queryDetial(Integer fromid,Integer toid){return ld.queryDetail(fromid,toid);}
    public int add(Integer fromid,Integer toid,String content){return ld.add(fromid,toid,content);}
    public Integer upstatus(Integer fromid,Integer toid){return ld.upstatus(fromid,toid);}
    public Integer updateAll(Integer toid){return ld.updateAll(toid);}
    public Integer queryNo(Integer toid){return ld.queryNo(toid);}
}
