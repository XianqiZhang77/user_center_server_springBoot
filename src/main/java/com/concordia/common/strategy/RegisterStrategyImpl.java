package com.concordia.common.strategy;

import com.concordia.common.util.MD5Utils;
import com.concordia.common.util.UUIDUtils;
import com.concordia.dao.RegisterRecordDao;
import com.concordia.dao.UserDao;
import com.concordia.entity.RegisterRecord;
import com.concordia.entity.User;
import com.concordia.entity.rpcDomain.common.RespResult;
import com.concordia.entity.rpcDomain.common.ResultCode;
import com.concordia.entity.rpcDomain.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterStrategyImpl implements UserStrategy{

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Autowired
    private UserDao userDao;

    @Override
    public RespResult doProcess(RegisterRequest registerRequest, OperatorStrategyEnum strategyEnum) {
        if (strategyEnum == OperatorStrategyEnum.SUCCESS) {
            RegisterRecord registerRecord = new RegisterRecord();
            registerRecord.setId(UUIDUtils.getUUID());
            registerRecord.setEmail(registerRecord.getEmail());
            registerRecord.setCaptcha(registerRequest.getCaptcha());
            registerRecord.setSendTime(new Date(System.currentTimeMillis()));
            registerRecordDao.save(registerRecord);

            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(MD5Utils.getMD5(registerRequest.getPassword()));
            user.setId(UUIDUtils.getUUID());
            userDao.save(user);
            return new RespResult(ResultCode.REGISTER_CAPICHA_SENT);

        } else {
            return new RespResult(ResultCode.MAIL_SEND_FAIL);
        }
    }
}