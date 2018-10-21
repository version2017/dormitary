package com.merit.entity;

/**
 * Created by R on 2018/6/15.
 */
public class Person {

    private int emplId;

    private String name;

    private int sex;

    private String phonNum;

    private String wechat;

    //外键
    private int dormId;
    private int sectId;

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhonNum() {
        return phonNum;
    }

    public void setPhonNum(String phonNum) {
        this.phonNum = phonNum;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public int getDormId() {
        return dormId;
    }

    public void setDormId(int dormId) {
        this.dormId = dormId;
    }

    public int getSectId() {
        return sectId;
    }

    public void setSectId(int sectId) {
        this.sectId = sectId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "emplId=" + emplId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phonNum='" + phonNum + '\'' +
                ", wechat='" + wechat + '\'' +
                ", dormId=" + dormId +
                ", sectId=" + sectId +
                '}';
    }
}
