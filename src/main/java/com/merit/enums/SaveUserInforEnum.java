package com.merit.enums;

/**
 * Created by R on 2018/6/15.
 */
public enum SaveUserInforEnum {
    SUCCESS(0, "保存成功"),
    HANDLE_FAILED(-1, "保存失败"),
    PARAMETER_LOSS(-2, "参数缺失，信息不全"),
    WRONG_EMPL_ID(-3, "系统中没有该工号记录，请检查工号或联系管理员"),
    WRONG_EMPL_ID_OR_NAME(-4, "工号或姓名有误，请检查工号和姓名"),
    DATA_CONVERT_ERROR(-5, "ID数据类型转化错误");

    private int state;

    private String stateInfor;

    SaveUserInforEnum(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public int getState() {
        return state;
    }

    public String getStateInfor() {
        return stateInfor;
    }

    public static SaveUserInforEnum stateOf(int index){
        for(SaveUserInforEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
