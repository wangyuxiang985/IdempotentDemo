package com.wyx.idempotent.controller;

import com.wyx.idempotent.annotation.AccessLimit;
import com.wyx.idempotent.annotation.ApiIdempotent;
import com.wyx.idempotent.common.ServerResponse;
import com.wyx.idempotent.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author yuxiang
 * @date 2019/8/23 16:12
 **/
@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("testIdempotence")
    @ApiIdempotent
    public ServerResponse testIdempotence(){
        return testService.testIdempotence();
    }

    @PostMapping("testAccessLimit")
    @AccessLimit(maxCount = 5,seconds = 30)
    public ServerResponse testAccessLimit(){
        return testService.accessLimit();
    }
}
