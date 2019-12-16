package com.athena.athena.common;

public enum HResult {
    H_OK(1,"成功"),H_SAID_HAS(2,"ID重复"),H_NO_DATA(-1,"没有数据"),REPLY_ID(10,"REPLY_ID"),SAID(20,"SAID"),REPLY(30,"REPLY");

    private int i ;
    private String msg;
    HResult(int i, String msg) {
        this.i = i;
        this.msg = msg;
    }

    public int getI() {
        return i;
    }

    public String getMsg() {
        return msg;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
