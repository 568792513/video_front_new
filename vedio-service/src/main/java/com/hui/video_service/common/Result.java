package com.hui.video_service.common;

public class Result<T> {

    public static final int SUCCESS = 0;// 成功

    public static final int UNLOGIN = 1000;// 鉴权失败,需要登录

    public static final int noRole = 403;// 鉴权失败,需要登录

    public static final int DANGER_CHAR = 1001;// 存在危险字符

    public static final int PARAM_VALIDATE_FAILED = 2000;// 参数校验失败

    public static final int PARAM_ERROR = 2001;// 参数错误

    public static final int SERVER_ERROR = 5000;// 内部出错

    private int code = 0;

    private String msg = "";

    private String userMsg = "";

    private T data = null;

    private Object dataExt = null;

    public Result() {
    }

    public Object getDataExt() {
        return dataExt;
    }

    public void setDataExt(Object dataExt) {
        this.dataExt = dataExt;
    }

    public Result(T data) {
        this.data = data;
        this.code = SUCCESS;
        this.msg = "success";
        this.userMsg = "成功";
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String msg, String userMsg, T data) {
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, String userMsg) {
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
    }

    public Result(int code, String msg, String userMsg, T data, Object dataExt) {
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
        this.data = data;
        this.dataExt = dataExt;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
