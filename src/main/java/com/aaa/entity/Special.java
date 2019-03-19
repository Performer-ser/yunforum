package com.aaa.entity;

import java.util.Date;

public class Special {
    private Integer specialid;
    private Integer lableid;
    private String title;
    private String content;
    private Integer userid;
    private Date time;

    public Integer getSpecialid() {
        return specialid;
    }

    public void setSpecialid(Integer specialid) {
        this.specialid = specialid;
    }

    public Integer getLableid() {
        return lableid;
    }

    public void setLableid(Integer lableid) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Special{" +
                "specialid=" + specialid +
                ", lableid=" + lableid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", time=" + time +
                '}';
    }
}
