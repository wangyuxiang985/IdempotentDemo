package com.wyx.idempotent.redislua.enums;

/**
 * //限流类型
**/
public enum LimitTypeEnum {

    /**
     * 自定义key
     */
    CUSTOMER,

    /**
     * 请求者IP
     */
    IP
}
