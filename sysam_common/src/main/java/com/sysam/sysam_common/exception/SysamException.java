package com.sysam.sysam_common.exception;

/**
 * @author huanyi
 * @version 1.0.0
 * @className SysamException
 * @description 自定义系统异常类
 * @date 2022-05-28
 */
public class SysamException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SysamException(String message) {
        super(message);
    }

    public SysamException(Throwable cause) {
        super(cause);
    }

    public SysamException(String message, Throwable cause) {
        super(message, cause);
    }
}
