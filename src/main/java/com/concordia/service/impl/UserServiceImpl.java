package com.concordia.service.impl;

import com.concordia.common.strategy.ContextMapper;
import com.concordia.common.strategy.OperatorStrategyEnum;
import com.concordia.component.exception.ValidateException;
import com.concordia.component.validate.ReqValidateManager;
import com.concordia.dao.UserDao;
import com.concordia.pojo.User;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.RegisterRequest;
import com.concordia.service.RegisterRecordService;
import com.concordia.service.ToolService;
import com.concordia.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private RegisterRecordService registerRecordService;

    @Autowired
    private UserDao userDao;

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

    @Override
    public boolean checkCaptcha(RegisterRequest registerRequest) throws NullPointerException{

        String captcha = registerRecordService.getCaptchaByUsername(registerRequest.getUsername());
        return StringUtils.equals(captcha, registerRequest.getCaptcha());
    }

    @Override
    public RespResult registerUser(RegisterRequest registerRequest) {
        User user = userDao.getByUsername(registerRequest.getUsername());
        user.setVerified(Boolean.TRUE);
        userDao.save(user);
        initUserInfo(user);
        return new RespResult(ResultCode.SUCCESS);
    }

    private void initUserInfo(User user) {

    }
}
