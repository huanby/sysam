package com.zxy.sysam_common.utils;

/**
 * ResultUtil.java
 * 返回包装工具类
 *
 * @param <T>
 * @author 作者
 * @since 2021-01-23
 */
public class ResultUtil<T> {

    /**
     * Http status
     */
    private Integer status;

    /**
     * message
     */
    private String message;

    /**
     * response
     */
    private T response;

    public ResultUtil(Integer i, String string, T response) {
        this.status = i;
        this.message = string;
        this.response = response;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
