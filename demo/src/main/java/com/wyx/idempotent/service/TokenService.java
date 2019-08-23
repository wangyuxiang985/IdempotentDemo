package com.wyx.idempotent.service;

import com.wyx.idempotent.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenService
 *
 * @author yuxiang
 * @date 2019/8/19 12:42
 **/
public interface TokenService {

    /**
     *生成token
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @return com.wyx.idempotent.common.ServerResponse
     */
    ServerResponse createToken();


    /**
     *检测token
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param request
     * @return void
     */
    void checkToken(HttpServletRequest request);
}
