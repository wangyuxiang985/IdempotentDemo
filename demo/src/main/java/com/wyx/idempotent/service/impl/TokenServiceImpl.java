package com.wyx.idempotent.service.impl;

import com.wyx.idempotent.common.Constant;
import com.wyx.idempotent.common.ResponseCode;
import com.wyx.idempotent.common.ServerResponse;
import com.wyx.idempotent.execption.ServiceException;
import com.wyx.idempotent.service.TokenService;
import com.wyx.idempotent.utils.JedisUtil;
import com.wyx.idempotent.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenServiceImpl
 *
 * @author yuxiang
 * @date 2019/8/19 12:50
 **/
@Service
public class TokenServiceImpl implements TokenService {


    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 生成token
     * <br/>
     *
     * @return com.wyx.idempotent.common.ServerResponse
     * @author yuxiang
     * @date 2019/8/19
     */
    @Override
    public ServerResponse createToken() {
        String uuid32 = RandomUtil.UUID32();
        StringBuilder token = new StringBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(uuid32);
        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);
        return ServerResponse.success(token.toString());
    }

    /**
     * 检测token
     * <br/>
     *
     * @param request
     * @return void
     * @author yuxiang
     * @date 2019/8/19
     */
    @Override
    public void checkToken(HttpServletRequest request) {

        String token = request.getHeader(TOKEN_NAME);
        if(StringUtils.isBlank(token)){
            //尝试从parameter中取
            token = request.getParameter(TOKEN_NAME);
            if(StringUtils.isBlank(token)){
                //token不存在
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }
        if(!jedisUtil.exists(token)){
            //redis不存在token
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
        Long del = jedisUtil.del(token);
        if(del <= 0){
            //key删除失败，加此判断用于并发删除
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
