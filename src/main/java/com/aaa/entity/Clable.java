package com.aaa.entity;

import java.util.Date;

public class Clable {
    private Integer clableid;
    private String clablename;
    private Integer typeid;
    private String cpic;
    private Date foundtime;
    private String synopsis;
    private Integer userid;

    public Integer getClableid() {
        return clableid;
    }

    public void setClableid(Integer clableid) {
        this.clableid = clableid;
    }

    public String getClablename() {
        return clablename;
    }

    public void setClablename(String clablename) {
        this.clablename = clablename;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getCpic() {
        return cpic;
    }

    public void setCpic(String cpic) {
        this.cpic = cpic;
    }

    public Date getFoundtime() {
        return foundtime;
    }

    public void setFoundtime(Date foundtime) {
        this.foundtime = foundtime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Clable(Integer clableid, String clablename, Integer typeid, String cpic, Date foundtime, String synopsis, Integer userid) {
        this.clableid = clableid;
        this.clablename = clablename;
        this.typeid = typeid;
        this.cpic = cpic;
        this.foundtime = foundtime;
        this.synopsis = synopsis;
        this.userid = userid;
    }

    public Clable() {
    }

    @Override
    public String toString() {
        return "Clable{" +
                "clableid=" + clableid +
                ", clablename='" + clablename + '\'' +
                ", typeid=" + typeid +
                ", cpic='" + cpic + '\'' +
                ", foundtime=" + foundtime +
                ", synopsis='" + synopsis + '\'' +
                ", userid=" + userid +
                '}';
    }
}
