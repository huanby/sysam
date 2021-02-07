package com.sysam.sysam_common.utils;

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


    public static Integer ERROR_CODE = -1;
    private static final String ERROR_MSG = "操作失败";

    public static Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "操作成功";


    /*构造函数*/
    public ResultUtil() {
        super();
        this.status = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
    }

    public ResultUtil(Integer i, String string, T response) {
        this.status = i;
        this.message = string;
        this.response = response;
    }


    public static ResultUtil ok(String message) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.message = message;
        return resultUtil;
    }

    public static ResultUtil error(String message) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.status = ERROR_CODE;
        resultUtil.message = message;
        return resultUtil;
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
