package com.concordia.service.impl;

import com.concordia.common.util.RandomCaptcha;
import com.concordia.rpcDomain.request.RegisterRequest;
import com.concordia.service.MailService;
import com.concordia.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private MailService mailService;

    @Override
    public String getCaptcha() {
        return null;
    }
    @Override
    public boolean sendRegisterMail(RegisterRequest registerRequest) {
        // captcha
        String captcha = RandomCaptcha.getCaptcha();
        registerRequest.setCaptcha(captcha);
        StringBuilder content = new StringBuilder();
        content.append("Hello ")
                .append(registerRequest.getUsername())
                .append("\n")
                .append("Here is your captcha number: ")
                .append(captcha);
        return mailService.sendSimpleMail(registerRequest.getEmail()
                , "Activate Your Account", content.toString());
    }
}
