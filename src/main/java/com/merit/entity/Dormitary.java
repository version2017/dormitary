package com.merit.entity;

import java.util.Date;

/**
 * Created by R on 2018/6/13.
 */
public class Dormitary {

    private int dormId;

    private String province;

    private String city;

    private String address;

    private String dormName;

    private String bedNum;

    private String occuNum;

    private Date rentDate;

    //退租日期
    private Date retireTime;

    private int isRetire;

    private String remarks;

    //所属项目外键
    private int projId;
    //宿舍管理员工号外键
    private int emplIdRef;

    public int getDormId() {
        return dormId;
    }

    public void setDormId(int dormId) {
        this.dormId = dormId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public String getOccuNum() {
        return occuNum;
    }

    public void setOccuNum(String occuNum) {
        this.occuNum = occuNum;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getRetireTime() {
        return retireTime;
    }

    public void setRetireTime(Date retireTime) {
        this.retireTime = retireTime;
    }

    public int getIsRetire() {
        return isRetire;
    }

    public void setIsRetire(int isRetire) {
        this.isRetire = isRetire;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public int getEmplIdRef() {
        return emplIdRef;
    }

    public void setEmplIdRef(int emplIdRef) {
        this.emplIdRef = emplIdRef;
    }

    @Override
    public String toString() {
        return "Dormitary{" +
                "dormId=" + dormId +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", dormName='" + dormName + '\'' +
                ", bedNum='" + bedNum + '\'' +
                ", occuNum='" + occuNum + '\'' +
                ", rentDate=" + rentDate +
                ", retireTime=" + retireTime +
                ", isRetire=" + isRetire +
                ", remarks='" + remarks + '\'' +
                ", projId=" + projId +
                ", emplIdRef=" + emplIdRef +
                '}';
    }
}
