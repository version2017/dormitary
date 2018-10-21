package com.merit.entity;

/**
 * Created by R on 2018/7/11.
 */
public class Project {

    private int projId;

    private String projName;

    //所属部门外键
    private int sectId;
    //项目经理外键
    private int projMana;

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public int getSectId() {
        return sectId;
    }

    public void setSectId(int sectId) {
        this.sectId = sectId;
    }

    public int getProjMana() {
        return projMana;
    }

    public void setProjMana(int projMana) {
        this.projMana = projMana;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projId=" + projId +
                ", projName='" + projName + '\'' +
                ", sectId=" + sectId +
                ", projMana=" + projMana +
                '}';
    }
}
