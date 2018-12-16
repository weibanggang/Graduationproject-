package com.wbg.sums.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;


public class MemberInfomationDto {

    private int mId;
    private String mUser;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public String getmQq() {
        return mQq;
    }

    public void setmQq(String mQq) {
        this.mQq = mQq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getrAdmissionDate() {
        return rAdmissionDate;
    }

    public void setrAdmissionDate(Date rAdmissionDate) {
        this.rAdmissionDate = rAdmissionDate;
    }

    public String getpRemarks() {
        return pRemarks;
    }

    public void setpRemarks(String pRemarks) {
        this.pRemarks = pRemarks;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcHeadmasterName() {
        return cHeadmasterName;
    }

    public void setcHeadmasterName(String cHeadmasterName) {
        this.cHeadmasterName = cHeadmasterName;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    private String mName;
    private String mSex;
    private String pPhoto;
    private String mQq;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rAdmissionDate;
    private String pRemarks;
    private String cName;
    private String cHeadmasterName;
    private String cPhone;
    private String dName;
    private String pName;
    private String eName;
    private String jName;
}
