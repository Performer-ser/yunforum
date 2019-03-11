package com.aaa.entity;

import java.util.Date;
/*
*  该实体类为浏览表
* */
public class Browse {
    private Integer browseid;
    private Integer type;
    private Integer composeid;
    private String userid;
    private Date browsetime;

    public Browse(Integer browseid, Integer type, Integer composeid, String userid, Date browsetime) {
        this.browseid = browseid;
        this.type = type;
        this.composeid = composeid;
        this.userid = userid;
        this.browsetime = browsetime;
    }

    public Browse() {
    }

    public Integer getBrowseid() {
        return browseid;
    }

    public void setBrowseid(Integer browseid) {
        this.browseid = browseid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getComposeid() {
        return composeid;
    }

    public void setComposeid(Integer composeid) {
        this.composeid = composeid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getBrowsetime() {
        return browsetime;
    }

    public void setBrowsetime(Date browsetime) {
        this.browsetime = browsetime;
    }

    @Override
    public String toString() {
        return "Browse{" +
                "browseid=" + browseid +
                ", type=" + type +
                ", composeid=" + composeid +
                ", userid='" + userid + '\'' +
                ", browsetime=" + browsetime +
                '}';
    }
}
