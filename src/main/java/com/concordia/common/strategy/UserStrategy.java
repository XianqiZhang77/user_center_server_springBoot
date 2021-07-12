package com.concordia.common.strategy;

import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserStrategy {

    RespResult doProcess(RegisterRequest registerRequest, OperatorStrategyEnum strategyEnum);
}
