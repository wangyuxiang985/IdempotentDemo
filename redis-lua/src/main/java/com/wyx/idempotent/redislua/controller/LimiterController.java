package com.wyx.idempotent.redislua.controller;

import com.wyx.idempotent.redislua.enums.LimitTypeEnum;
import com.wyx.idempotent.redislua.handle.limit.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName LimiterController
 * @Description api层测试
 **/
@RestController
public class LimiterController {

    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();

    /**
     * 在10秒内只允许放行3个请求
     * @api {get} /limitTest1
     */
    @Limit(key = "limitTest", period = 10, count = 3)
    @GetMapping("/limitTest1")
    public int testLimiter1() {

        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    /**
     * 自定义key 在10秒内只允许放行3个请求
     * @api {get} /limitTest2
     */
    @Limit(key = "customer_limit_test", period = 10, count = 3, limitType = LimitTypeEnum.CUSTOMER)
    @GetMapping("/limitTest2")
    public int testLimiter2() {
        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    /**
     * ip限流 在10秒内只允许放行3个请求
     * @api {get} /limitTest3
     */
    @Limit(key = "ip_limit_test", period = 10, count = 3, limitType = LimitTypeEnum.IP)
    @GetMapping("/limitTest3")
    public int testLimiter3() {
        return ATOMIC_INTEGER_3.incrementAndGet();
    }
}
