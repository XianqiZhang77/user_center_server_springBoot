package com.concordia.service.impl;

import com.concordia.common.strategy.ContextMapper;
import com.concordia.common.strategy.OperatorStrategyEnum;
import com.concordia.component.exception.ValidateException;
import com.concordia.component.validate.ReqValidateManager;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.RegisterRequest;
import com.concordia.service.ToolService;
import com.concordia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ReqValidateManager reqValidateManager;

    @Autowired
    private ToolService toolService;

    @Autowired
    private ContextMapper contextMapper;

    @Override
    public RespResult beforeRegister(RegisterRequest registerRequest) {
        //参数校验
        try {
            reqValidateManager.doExecute(registerRequest);
        } catch (ValidateException ex) {
            System.out.println(ex.getResultCode());
            return new RespResult(ex.getResultCode());
        }

        //send email
        boolean isSend = toolService.sendRegisterMail(registerRequest);
        OperatorStrategyEnum context =
                isSend ? OperatorStrategyEnum.SUCCESS : OperatorStrategyEnum.FAIL;

        return contextMapper.loadProcessor(context).doProcess(registerRequest, context);
    }

}
