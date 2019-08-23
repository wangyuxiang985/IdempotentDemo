package com.wyx.idempotent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ApiIdempotent
 *在需要保证 接口幂等性 的Controller的方法上使用此注解
 * @author yuxiang
 * @date 2019/8/19 11:45
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
