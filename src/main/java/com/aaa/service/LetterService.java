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
}
