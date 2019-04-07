package com.aaa.entity;

import java.util.Date;

public class Userinfo {
    private Integer userid;
    private String username;
    private Integer sex;
    private Date birthday;
    private String phone;
    private String address;
    private String idcard;
    private String head;
    private String truename;
    private Integer rank;
    private Date createuser;
    private Integer userstatus;
    private String signature;
    private String pwd;

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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Userinfo() {
    }

    public Userinfo(Integer userid, String username, Integer sex, Date birthday, String phone, String address, String idcard, String head, String truename, Integer rank, Date createuser, Integer userstatus, String signature, String pwd) {
        this.userid = userid;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.idcard = idcard;
        this.head = head;
        this.truename = truename;
        this.rank = rank;
        this.createuser = createuser;
        this.userstatus = userstatus;
        this.signature = signature;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", idcard='" + idcard + '\'' +
                ", head='" + head + '\'' +
                ", truename='" + truename + '\'' +
                ", rank=" + rank +
                ", createuser=" + createuser +
                ", userstatus=" + userstatus +
                ", signature='" + signature + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}