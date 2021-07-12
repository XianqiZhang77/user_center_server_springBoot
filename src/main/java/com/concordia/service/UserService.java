package com.concordia.service;

import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RespResult beforeRegister(RegisterRequest registerRequest);

}
