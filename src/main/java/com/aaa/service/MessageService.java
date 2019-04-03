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
}
