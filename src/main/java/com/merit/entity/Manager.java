package com.merit.entity;

/**
 * Created by R on 2018/6/15.
 */
public class Manager {

    private int manaId;

    private String manaAccount;

    private String manaPassword;

    public int getManaId() {
        return manaId;
    }

    public void setManaId(int manaId) {
        this.manaId = manaId;
    }

    public String getManaAccount() {
        return manaAccount;
    }

    public void setManaAccount(String manaAccount) {
        this.manaAccount = manaAccount;
    }

    public String getManaPassword() {
        return manaPassword;
    }

    public void setManaPassword(String manaPassword) {
        this.manaPassword = manaPassword;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "manaId=" + manaId +
                ", manaAccount='" + manaAccount + '\'' +
                ", manaPassword='" + manaPassword + '\'' +
                '}';
    }
}
