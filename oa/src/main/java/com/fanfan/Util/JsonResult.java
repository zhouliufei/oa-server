package com.fanfan.Util;

/**
 * @Description:
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 */
public class JsonResult<T> {

    private String msg;

    private T data;

    private int status;

    private String ok;

    public static JsonResult ok() {
        return new JsonResult(null,null,200);
    }

    public static JsonResult errorMessage(String msg) {
        return new JsonResult(msg,null,500);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(msg,null,502);
    }

    public JsonResult() {}

    public JsonResult(T data) {
        setObject(data);
    }

    public JsonResult(String msg, T data, int status) {
        this.msg = msg;
        this.data = data;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObject() {
        return data;
    }

    public void setObject(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", ok='" + ok + '\'' +
                '}';
    }

}
