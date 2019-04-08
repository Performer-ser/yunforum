package com.aaa.entity;

import java.util.Date;

public class Perspecial {
    private Integer perspid;
    private String perspname;
    private String perspdescribe;
    private Integer userid;
    private Date time;

    public Integer getPerspid() {
        return perspid;
    }

    public void setPerspid(Integer perspid) {
        this.perspid = perspid;
    }

    public String getPerspname() {
        return perspname;
    }

    public void setPerspname(String perspname) {
        this.perspname = perspname;
    }

    public String getPerspdescribe() {
        return perspdescribe;
    }

    public void setPerspdescribe(String perspdescribe) {
        this.perspdescribe = perspdescribe;
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
        return "Perspecial{" +
                "perspid=" + perspid +
                ", perspname='" + perspname + '\'' +
                ", perspdescribe='" + perspdescribe + '\'' +
                ", userid=" + userid +
                ", time=" + time +
                '}';
    }
}
