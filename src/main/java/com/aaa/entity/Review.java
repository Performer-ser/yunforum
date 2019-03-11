package com.aaa.entity;

import java.util.Date;
/*
* 该实体类为评论表
* */
public class Review {
    private Integer reviewid;
    private Integer composeid;
    private Integer type;
    private String content;
    private Integer userid;
    private Date time;

    public Review(Integer reviewid, Integer composeid, Integer type, String content, Integer userid, Date time) {
        this.reviewid = reviewid;
        this.composeid = composeid;
        this.type = type;
        this.content = content;
        this.userid = userid;
        this.time = time;
    }

    public Review() {
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getComposeid() {
        return composeid;
    }

    public void setComposeid(Integer composeid) {
        this.composeid = composeid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewid=" + reviewid +
                ", composeid=" + composeid +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", time=" + time +
                '}';
    }
}
