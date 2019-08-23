package com.wyx.idempotent.config;

import com.wyx.idempotent.interceptor.ApiIdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *注册拦截器
 * @author yuxiang
 * @date 2019/8/19 14:11
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getApiIdempotentInterceptor());
    }

    @Bean
    public ApiIdempotentInterceptor getApiIdempotentInterceptor(){
        return new ApiIdempotentInterceptor();
    }
}
