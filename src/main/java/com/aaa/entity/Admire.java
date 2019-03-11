package com.aaa.entity;

import java.util.Date;

public class Admire {
    private Integer admireid;
    private Integer type_id;
    private Integer type;
    private Integer userid;
    private Integer status;
    private Date time;

    public Integer getAdmireid() {
        return admireid;
    }

    public Integer getType_id() {
        return type_id;
    }

    public Integer getType() {
        return type;
    }

    public Integer getUserid() {
        return userid;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getTime() {
        return time;
    }

    public void setAdmireid(Integer admireid) {
        this.admireid = admireid;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Admire{" +
                "admireid=" + admireid +
                ", type_id=" + type_id +
                ", type=" + type +
                ", userid=" + userid +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
//    点赞根据文章id和用户id首先查询评论表和回复表中的数据，查询点赞表中根据点赞类型查找其中有“我”的点赞没和点赞数量（状态）；
}
