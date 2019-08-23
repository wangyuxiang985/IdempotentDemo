package com.wyx.idempotent.controller;

import com.wyx.idempotent.common.ServerResponse;
import com.wyx.idempotent.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenController
 *
 * @author yuxiang
 * @date 2019/8/23 15:54
 **/
@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("get_token")
    public ServerResponse getToken(){
        return tokenService.createToken();
    }
}
