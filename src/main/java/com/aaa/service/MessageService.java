package com.aaa.service;

import com.aaa.dao.MessageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Resource
    MessageDao md;
    public List<Map<String,Object>> queryMess(Integer userid){return md.queryMess(userid);}
    public List<Map<String,Object>> queryAllUser(Integer from_userid,Integer to_userid){return md.queryAttUser(from_userid,to_userid);}
    public Integer addUser(Integer from_userid,Integer to_userid){return md.addUser( from_userid, to_userid);}
    public Integer delUser(Integer from_userid,Integer to_userid){return md.delUser( from_userid, to_userid);}

    public List<Map<String,Object>> queryAllSpe(Integer perspid,Integer userid){return md.queryAttSpe(perspid,userid);}
    public Integer addSpe(Integer perspid,Integer userid){return md.addSpe( perspid, userid);}
    public Integer delSpe(Integer perspid,Integer userid){return md.delSpe( perspid, userid);}

    public List<Map<String,Object>> queryAllQue(Integer questionid,Integer userid){return md.queryAttQue(questionid,userid);}
    public Integer addQue(Integer questionid,Integer userid){return md.addQue( questionid, userid);}
    public Integer delQue(Integer questionid,Integer userid){return md.delQue( questionid, userid);}

    public List<Map<String,Object>> queryAllLab(Integer lableid,Integer userid){return md.queryAttLab(lableid,userid);}
    public Integer addLab(Integer lableid,Integer userid){return md.addLab( lableid, userid);}
    public Integer delLab(Integer lableid,Integer userid){return md.delLab( lableid, userid);}
}
