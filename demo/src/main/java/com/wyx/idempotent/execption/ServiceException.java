package com.wyx.idempotent.execption;

import lombok.Getter;
import lombok.Setter;

/**
 * ServiceException
 *用于抛出业务逻辑异常
 * @author yuxiang
 * @date 2019/8/19 13:06
 **/
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private String code;
    private String msg;

    public ServiceException() {
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
