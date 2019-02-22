package com.wbg.sums.entity;

public class Roles {
    int rId;
    String rName;
    String rRemarks;

    public int getrId() {
        return rId;
    }

    public String getrRemarks() {
        return rRemarks;
    }

    public void setrRemarks(String rRemarks) {
        this.rRemarks = rRemarks;
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

}
