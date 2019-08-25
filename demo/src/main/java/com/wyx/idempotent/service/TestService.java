package com.wyx.idempotent.service;

import com.wyx.idempotent.common.ServerResponse;

/**
 * TestService
 *
 * @author yuxiang
 * @date 2019/8/23 16:03
 **/
public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();
}
