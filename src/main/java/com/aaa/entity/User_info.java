package com.aaa.entity;

import java.util.Date;

public class User_info {
    private Integer userid;
    private String username;
    private Integer sex;
    private Date birthday;
    private String phone;
    private String address;
    private String idcard;
    private String truename;
    private Integer rank;
    private String head;
    private Date createuser;
    private Integer userstatus;

    public User_info(Integer userid, String username, Integer sex, Date birthday, String phone, String address, String idcard, String truename, Integer rank, String head, Date createuser, Integer userstatus) {
        this.userid = userid;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.idcard = idcard;
        this.truename = truename;
        this.rank = rank;
        this.head = head;
        this.createuser = createuser;
        this.userstatus = userstatus;
    }

    public User_info() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Date createuser) {
        this.createuser = createuser;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    @Override
    public String toString() {
        return "User_info{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", idcard='" + idcard + '\'' +
                ", truename='" + truename + '\'' +
                ", rank=" + rank +
                ", head='" + head + '\'' +
                ", createuser=" + createuser +
                ", userstatus=" + userstatus +
                '}';
    }
}
