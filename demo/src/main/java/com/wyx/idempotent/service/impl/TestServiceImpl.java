package com.wyx.idempotent.service.impl;

import com.wyx.idempotent.common.ServerResponse;
import com.wyx.idempotent.service.TestService;
import org.springframework.stereotype.Service;

/**
 * TestServiceImpl
 *
 * @author yuxiang
 * @date 2019/8/23 16:04
 **/
@Service
public class TestServiceImpl implements TestService {

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }
}
