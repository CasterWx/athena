package com.athena.athena.common;

public class Result<T> {
    private HResult status;
    private String message;
    private T data;

    public Result(HResult status){
        this.status = status;
    }

    public void setStatus(HResult status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
