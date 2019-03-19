package com.aaa.entity;


import java.util.List;

public class Question {
    private Integer questionid;
    private String lableid;
    private String title;
    private String content;
    private Integer userid;
    private String time;
    private Integer reviewid;

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getLableid() {
        return lableid;
    }

    public void setLableid(String lableid) {
        this.lableid = lableid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionid=" + questionid +
                ", lableid='" + lableid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", time='" + time + '\'' +
                ", reviewid=" + reviewid +
                '}';
    }
}
