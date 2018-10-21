package com.merit.entity;

/**
 * Created by R on 2018/6/15.
 */
public class Sector {

    private int sectId;

    private String sectName;

    private String persNum;

    //部门经理外键
    private int sectMana;

    public int getSectId() {
        return sectId;
    }

    public void setSectId(int sectId) {
        this.sectId = sectId;
    }

    public String getSectName() {
        return sectName;
    }

    public void setSectName(String sectName) {
        this.sectName = sectName;
    }

    public String getPersNum() {
        return persNum;
    }

    public void setPersNum(String persNum) {
        this.persNum = persNum;
    }

    public int getSectMana() {
        return sectMana;
    }

    public void setSectMana(int sectMana) {
        this.sectMana = sectMana;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "sectId=" + sectId +
                ", sectName='" + sectName + '\'' +
                ", persNum='" + persNum + '\'' +
                ", sectMana=" + sectMana +
                '}';
    }
}
