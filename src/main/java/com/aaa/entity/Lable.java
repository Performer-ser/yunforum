package com.aaa.entity;

public class Lable {
    private Integer lableid;
    private String lablename;
    private String lablepic;
    private String describe;
    private String latypeid;

    public Integer getLableid() {
        return lableid;
    }

    public void setLableid(Integer lableid) {
        this.lableid = lableid;
    }

    public String getLablename() {
        return lablename;
    }

    public void setLablename(String labelname) {
        this.lablename = labelname;
    }

    public String getLablepic() {
        return lablepic;
    }

    public void setLablepic(String lablepic) {
        this.lablepic = lablepic;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Lable{" +
                "lableid=" + lableid +
                ", lablename='" + lablename + '\'' +
                ", lablepic='" + lablepic + '\'' +
                ", describe='" + describe + '\'' +
                ", latypeid='" + latypeid + '\'' +
                '}';
    }

    public String getLatypeid() {
        return latypeid;
    }

    public void setLatypeid(String latypeid) {
        this.latypeid = latypeid;
    }
}
