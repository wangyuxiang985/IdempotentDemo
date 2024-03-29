package com.wyx.idempotent.redislua.handle.limit;

import com.wyx.idempotent.redislua.enums.LimitTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义限流注解
**/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limit {

    /**
     * 名字
     */
    String name() default "";

    /**
     * key
     */
    String key() default "";

    /**
     * Key的前缀
     */
    String prefix() default "";

    /**
     * 给定的时间范围 单位(秒)
     */
    int period();

    /**
     * 一定时间内最多访问次数
     */
    int count();

    /**
     * 限流的类型(用户自定义key 或者 请求ip)
     */
    LimitTypeEnum limitType() default LimitTypeEnum.CUSTOMER;
}
