package com.aaa.entity;

import java.util.Date;

public class Reply {
    private Integer replyid;
    private Integer reviewid;
    private String content;
    private Integer from_userid;
    private Integer to_userid;
    private Date recoverytime;

    public Reply() {
    }

    public Reply(Integer replyid, Integer reviewid, String content, Integer from_userid, Integer to_userid, Date recoverytime) {
        this.replyid = replyid;
        this.reviewid = reviewid;
        this.content = content;
        this.from_userid = from_userid;
        this.to_userid = to_userid;
        this.recoverytime = recoverytime;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyid=" + replyid +
                ", reviewid=" + reviewid +
                ", content='" + content + '\'' +
                ", from_userid=" + from_userid +
                ", to_userid=" + to_userid +
                ", recoverytime=" + recoverytime +
                '}';
    }

    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFrom_userid() {
        return from_userid;
    }

    public void setFrom_userid(Integer from_userid) {
        this.from_userid = from_userid;
    }

    public Integer getTo_userid() {
        return to_userid;
    }

    public void setTo_userid(Integer to_userid) {
        this.to_userid = to_userid;
    }

    public Date getRecoverytime() {
        return recoverytime;
    }

    public void setRecoverytime(Date recoverytime) {
        this.recoverytime = recoverytime;
    }
}
