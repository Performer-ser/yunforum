package com.aaa.entity;

import java.util.Date;

public class Clique {
    private Integer cid;
    private Integer userid;
    private Integer clableid;
    private Integer status;
    private Date ctime;

    @Override
    public String toString() {
        return "Clique{" +
                "cid=" + cid +
                ", userid=" + userid +
                ", clableid=" + clableid +
                ", status=" + status +
                ", ctime=" + ctime +
                '}';
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Clique(Integer cid, Integer userid, Integer clableid, Integer status, Date ctime) {
        this.cid = cid;
        this.userid = userid;
        this.clableid = clableid;
        this.status = status;
        this.ctime = ctime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getClableid() {
        return clableid;
    }

    public void setClableid(Integer clableid) {
        this.clableid = clableid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Clique() {
    }


}
