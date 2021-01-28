package com.zxy.sysam_task.utils;

import java.io.Serializable;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.utils
 * @ClassName: BaseResult
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/28 13:56
 * @Version: 1.0
 */
public class BaseResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String code;
    /**
     * 状态描述
     */
    private String message;
    /**
     * 业务数据
     */
    private Object data;

    /**
     * 用户自定义返回错误CODE
     */
    public static String ERROR_CODE = "-1";
    private static String ERROR_MSG = "操作失败";

    public static String SUCCESS_CODE = "200";
    private static String SUCCESS_MSG = "操作成功";

    /**
     * 构造方法
     */
    public BaseResult() {
        super();
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
    }

    public BaseResult(Object data) {
        super();
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态描述
     */
    public BaseResult(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


    public BaseResult(String code, String message, String data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态描述
     * @param data    业务数据
     */
    public BaseResult(String code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 状态码
     */
    public String getCode() {
        return code;
    }

    /**
     * 状态码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 状态描述
     */
    public String getMessage() {
        return message;
    }

    /**
     * 状态描述
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 业务数据
     */
    public Object getData() {
        return data;
    }

    /**
     * 业务数据
     */
    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
