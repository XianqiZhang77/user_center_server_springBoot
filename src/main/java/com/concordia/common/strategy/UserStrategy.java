package com.concordia.common.strategy;

import com.concordia.entity.rpcDomain.common.RespResult;
import com.concordia.entity.rpcDomain.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserStrategy {

    RespResult doProcess(RegisterRequest registerRequest, OperatorStrategyEnum strategyEnum);
}
