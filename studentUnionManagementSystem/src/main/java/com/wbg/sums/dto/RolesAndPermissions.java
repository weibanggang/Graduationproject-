package com.wbg.sums.dto;

public class RolesAndPermissions {
    int id;
    int rId;
    String rName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrRemarks() {
        return rRemarks;
    }

    public void setrRemarks(String rRemarks) {
        this.rRemarks = rRemarks;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String rRemarks;
    private Integer pId;
    private String name;
    private String url;

}
