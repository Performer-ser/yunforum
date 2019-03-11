package com.aaa.entity;

import java.util.Date;

public class Circle {
    private Integer circleid;
    private Integer clableid;
    private String title;
    private String content;
    private Integer userid;
    private Date time;

    public Integer getCircleid() {
        return circleid;
    }

    public void setCircleid(Integer circleid) {
        this.circleid = circleid;
    }

    public Integer getClableid() {
        return clableid;
    }

    public void setClableid(Integer clableid) {
        this.clableid = clableid;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Circle(Integer circleid, Integer clableid, String title, String content, Integer userid, Date time) {
        this.circleid = circleid;
        this.clableid = clableid;
        this.title = title;
        this.content = content;
        this.userid = userid;
        this.time = time;
    }

    public Circle() {
    }

    @Override
    public String toString() {
        return "Circle{" +
                "circleid=" + circleid +
                ", clableid=" + clableid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", time=" + time +
                '}';
    }
}
