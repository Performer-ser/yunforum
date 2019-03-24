package com.aaa.entity;

public class Lable {
    private Integer lableid;
    private String lablename;
    private String lablepic;
    private String describe;
    private Integer latypeid;

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

    public Integer getLatypeid() {
        return latypeid;
    }

    public void setLatypeid(Integer latypeid) {
        this.latypeid = latypeid;
    }

    @Override
    public String toString() {
        return "Lable{" +
                "lableid=" + lableid +
                ", lablename='" + lablename + '\'' +
                ", lablepic='" + lablepic + '\'' +
                ", describe='" + describe + '\'' +
                ", latypeid=" + latypeid +
                '}';
    }
}
