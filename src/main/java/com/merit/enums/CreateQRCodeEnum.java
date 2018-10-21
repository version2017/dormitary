package com.merit.enums;

/**
 * Created by R on 2018/6/15.
 */
public enum CreateQRCodeEnum {
    SUCCESS(0, "生成成功"),
    CREATE_FAILED(-1, "生成失败");

    private int state;

    private String stateInfor;

    CreateQRCodeEnum(int state, String stateInfor) {
        this.state = state;
        this.stateInfor = stateInfor;
    }

    public int getState() {
        return state;
    }

    public String getStateInfor() {
        return stateInfor;
    }

    public static CreateQRCodeEnum stateOf(int index){
        for(CreateQRCodeEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
