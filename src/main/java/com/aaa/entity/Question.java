package com.aaa.entity;

import java.util.Date;

public class Question {
    private Integer questionid;
    private Integer qlableid;
    private String title;
    private String  content;
    private Integer userid;
    private Date time;

    public Integer getQuestionid() {
        return questionid;
    }

    public Integer getQlableid() {
        return qlableid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getUserid() {
        return userid;
    }

    public Date getTime() {
        return time;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public void setQlableid(Integer qlableid) {
        this.qlableid = qlableid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionid=" + questionid +
                ", qlableid=" + qlableid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", time=" + time +
                '}';
    }

}
