package com.aaa.service;

import com.aaa.dao.QuestionDao;
import com.aaa.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Resource
    QuestionDao qd;
    public int add(Question q){
        return qd.add(q);
    }
    public List<Map<String,Object>> queryQuestionsByQlable(Integer pageNum){

        Integer offset =  (pageNum - 1) * 15;
        List<Map<String, Object>> list = qd.queryQuestionsByQlable(offset);
        for (Map<String, Object> q:list){
            //System.out.println(q.get("qlablename"));
            List<String> ls = new ArrayList<String>();
            String s = q.get("lablename").toString();
            Integer ids = Integer.parseInt(q.get("questionid").toString());
            System.out.println("ids:"+ids);
            Integer nums = qd.byAdmire(ids);// 点赞数
            Integer bro = qd.byBrowse(ids);//浏览数量
            Integer rev = qd.byReview(ids);//回答数量
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String ss = new SimpleDateFormat("yyyy-MM-dd").format(q.get("time")).toString();
            try {
                Date times = sdf.parse(q.get("time").toString());
                Date d = new Date();
                long nd = 1000 * 24 * 60 * 60;
                long nh = 1000 * 60 * 60;
                long nm = 1000 * 60;
                long ns = 1000;
                // 获得两个时间的毫秒时间差异
                long diff =  d.getTime()- times.getTime();
                // 计算差多少天
                long day = diff / nd;
                System.out.println("day"+day);
                // 计算差多少小时
                long hour = diff % nd / nh;
                System.out.println("hour"+hour);
                // 计算差多少分钟
                long min = diff % nd % nh / nm;
                System.out.println(min+"min");
                // 计算差多少秒//输出结果
                long sec = diff % nd % nh % nm / ns;
                System.out.println("sec"+sec);
                if(day>30){
                    q.put("details",ss);
                }else if(day<=30&&day>=1){
                    q.put("details",day+"天前");
                }else if(day<1&&hour>=1){
                    q.put("details",hour+"小时前");
                }else if(day<1&&hour<1&&min>=1){
                    q.put("details",min+"分钟前");
                }else if(day<1&&hour<1&&min<1){
                    q.put("details","刚刚");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("nums:"+nums);
            q.put("nums",nums);
            q.put("bro",bro);
            q.put("rev",rev);
            String[] str = s.split(",");
            for(int i = 0;i<str.length;i++){
                ls.add(str[i]);
            }
            q.put("lablenames",ls);
            System.out.println(q.get("lablenames"));
        }
        return list;
    }
    //根据问答的id查询问答详情
    public List<Map<String,Object>> quesById(Integer qid){
        return qd.quesById(qid);
    }
    //查询点赞数量
    public Integer byAdmire(Integer qid){
        return qd.byAdmire(qid);
    }

    //查询评论回答数量
    public Integer byReview(Integer qid){
        return qd.byReview(qid);
    }
    //查询浏览数量
    public Integer byBrowse(Integer qid){
        return qd.byBrowse(qid);
    }
    public Map<String,Object> queryTitle(Integer qid){return qd.queryTitle(qid);}
    public List<Question> queryUser(Integer userid,Integer page){return qd.queryUser(userid,page);}
    public List<Map<String,Object>> queryUserAll(Integer userid){return qd.queryUserAll(userid);}

    //查看回答表及回答用户根据问答的
    public List<Map<String,Object>> queryReviewByQuestionid(Integer qid){
        return qd.queryReviewByQuestionid(qid);
    }
    //查看所有标签类型
    public List<Latype> queryLatype(){
        return qd.queryLatype();
    }
    ////查询标签及标签类型根据标签类型id
    public List<Lable> queryLableByLatypeid(){
        return qd.queryLableByLatypeid();
    }
    //查看标签详情根据标签名称得到标签id传到详情页面
    public int queryLableByLablename(String lablename){
        return qd.queryLableByLablename(lablename);
    }
    //添加浏览数据
    public Integer addBrowse(Browse browse){
        return qd.addBrowse(browse);
    }
    //查询问答的关注数量
    public Integer byAttentionQuestion(Integer questionid){
        return qd.byAttentionQuestion(questionid);
    }
    //添加问答书记
    public void addspe(Question que){
        qd.addspe(que);
    }
    public List<Lable> querylablename(String lablename){
        return qd.querylablename(lablename);
    }
    public Integer addByReview(Review r){
        return  qd.addByReview(r);
    }
    public List<Review> queryAllAnwser(Integer userid){return qd.queryAllAnswer(userid);}
    //添加点赞表数据
    public Integer addAdmire(Admire a){
        return qd.addAdmire(a);
    }
    public List<Map<String,Object>> queryQuestionid(Integer qid){
        return qd.queryQuestionid(qid);
    }
    //查看是否关注
    public List<Map<String,Object>> Guanzhu(Integer uid,Integer queryqid){
        return qd.Guanzhu(uid,queryqid);
    }
    //添加采纳 到问答表的reviewid列
    public Integer addCaina(Integer reviewid,Integer questionid){
        return qd.addCaina(reviewid,questionid);
    }
    //查看是否采纳
    public List<Map<String,Object>> queryCaina(Integer uid,Integer rid){
        return qd.queryCaina(uid,rid);
    }

}