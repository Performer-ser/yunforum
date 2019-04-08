package com.aaa.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Letter {
    private int letterid;
    private int fromid;
    private int toid;
    private String content;
    private Date time;
    private int status;

    public int getLetterid() {
        return letterid;
    }

    public void setLetterid(int letterid) {
        this.letterid = letterid;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "letterid=" + letterid +
                ", fromid=" + fromid +
                ", toid=" + toid +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
