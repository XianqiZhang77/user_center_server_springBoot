package com.concordia.service;

import com.concordia.entity.rpcDomain.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface ToolService {

    boolean sendRegisterMail(RegisterRequest registerRequest);

    String getCaptcha();
}
