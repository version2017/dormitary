package com.merit.entity;

import java.util.Date;

/**
 * Created by R on 2018/6/15.
 */
public class RentApply {

    private int rentId;

    private String nAddress;

    private String nProject;

    private int nOccuNum;

    private Date nRentDate;

    //租约到期时间
    private Date nRetireTime;

    private int nPrice;

    private int nBedNum;

    //外键
    private int emplId;
    private int manaId;

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public String getnAddress() {
        return nAddress;
    }

    public void setnAddress(String nAddress) {
        this.nAddress = nAddress;
    }

    public String getnProject() {
        return nProject;
    }

    public void setnProject(String nProject) {
        this.nProject = nProject;
    }

    public int getnOccuNum() {
        return nOccuNum;
    }

    public void setnOccuNum(int nOccuNum) {
        this.nOccuNum = nOccuNum;
    }

    public Date getnRentDate() {
        return nRentDate;
    }

    public void setnRentDate(Date nRentDate) {
        this.nRentDate = nRentDate;
    }

    public Date getnRetireTime() {
        return nRetireTime;
    }

    public void setnRetireTime(Date nRetireTime) {
        this.nRetireTime = nRetireTime;
    }

    public int getnPrice() {
        return nPrice;
    }

    public void setnPrice(int nPrice) {
        this.nPrice = nPrice;
    }

    public int getnBedNum() {
        return nBedNum;
    }

    public void setnBedNum(int nBedNum) {
        this.nBedNum = nBedNum;
    }

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }

    public int getManaId() {
        return manaId;
    }

    public void setManaId(int manaId) {
        this.manaId = manaId;
    }

    @Override
    public String toString() {
        return "RentApply{" +
                "rentId=" + rentId +
                ", nAddress='" + nAddress + '\'' +
                ", nProject='" + nProject + '\'' +
                ", nOccuNum=" + nOccuNum +
                ", nRentDate=" + nRentDate +
                ", nRetireTime=" + nRetireTime +
                ", nPrice=" + nPrice +
                ", nBedNum=" + nBedNum +
                ", emplId=" + emplId +
                ", manaId=" + manaId +
                '}';
    }
}
