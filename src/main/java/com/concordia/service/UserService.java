package com.concordia.service;

import com.concordia.pojo.User;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.LoginRequest;
import com.concordia.rpcDomain.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RespResult beforeRegister(RegisterRequest registerRequest);

    boolean checkCaptcha(RegisterRequest registerRequest) throws NullPointerException;

    RespResult registerUser(RegisterRequest registerRequest);

    User getUserByUsername(String username);

    boolean checkVerified(User user);

    boolean checkPassword(User user, LoginRequest loginRequest);
}
