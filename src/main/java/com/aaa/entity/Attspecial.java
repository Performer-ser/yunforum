package com.aaa.entity;


public class Attspecial {
   private Integer attid;
   private Integer perspid;
   private Integer userid;

    public Integer getAttid() {
        return attid;
    }

    public void setAttid(Integer attid) {
        this.attid = attid;
    }

    public Integer getPerspid() {
        return perspid;
    }

    public void setPerspid(Integer perspid) {
        this.perspid = perspid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Attspecial{" +
                "attid=" + attid +
                ", perspid=" + perspid +
                ", userid=" + userid +
                '}';
    }
}
