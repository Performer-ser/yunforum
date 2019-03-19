package com.aaa.entity;

public class Slable {
    private Integer slableid;
    private String slablename;
    private Integer userid;

    public Integer getSlableid() {
        return slableid;
    }

    public void setSlableid(Integer slableid) {
        this.slableid = slableid;
    }

    public String getSlablename() {
        return slablename;
    }

    public void setSlablename(String slablename) {
        this.slablename = slablename;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Slable{" +
                "slableid=" + slableid +
                ", slablename='" + slablename + '\'' +
                ", userid=" + userid +
                '}';
    }
}
