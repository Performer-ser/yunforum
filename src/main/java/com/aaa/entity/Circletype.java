package com.aaa.entity;

public class Circletype {
    private Integer tid;
    private String tname;

    public Circletype(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public Circletype() {
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
   }

    @Override
    public String toString() {
        return "Circletype{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }
}
