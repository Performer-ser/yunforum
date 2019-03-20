package com.aaa.entity;

public class Latype {
    private Integer latypeid;
    private String  latypename;

    public Integer getLatypeid() {
        return latypeid;
    }

    public void setLatypeid(Integer latypeid) {
        this.latypeid = latypeid;
    }

    public String getLatypename() {
        return latypename;
    }

    public void setLatypename(String latypename) {
        this.latypename = latypename;
    }

    @Override
    public String toString() {
        return "Latype{" +
                "latypeid=" + latypeid +
                ", latypename='" + latypename + '\'' +
                '}';
    }
}
