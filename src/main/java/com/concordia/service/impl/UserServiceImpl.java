package com.concordia.service.impl;

import com.concordia.common.strategy.ContextMapper;
import com.concordia.common.strategy.OperatorStrategyEnum;
import com.concordia.common.util.MD5Utils;
import com.concordia.common.util.UUIDUtils;
import com.concordia.component.exception.ValidateException;
import com.concordia.component.validate.ReqValidateManager;
import com.concordia.dao.UserDao;
import com.concordia.pojo.*;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.LoginRequest;
import com.concordia.rpcDomain.request.RegisterRequest;
import com.concordia.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String>
        implements UserService {

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
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private UserProfileService userProfileService;
    
    @Autowired
    private UserPreferenceService userPreferenceService;
    
    @Autowired UserTagService userTagService;


    @Override
    protected JpaRepository getJpaRepository() {
        return userDao;
    }

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
    @Transactional(rollbackOn = Exception.class)
    public RespResult registerUser(RegisterRequest registerRequest) {
        User user = userDao.getByUsername(registerRequest.getUsername());
        user.setVerified(Boolean.TRUE);
        userDao.save(user);
        initUserInfo(user);
        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public boolean checkVerified(User user) {
        return user != null && user.getVerified();
    }

    @Override
    public boolean checkPassword(User user, LoginRequest loginRequest) {
        return StringUtils.equals(user.getPassword()
                , MD5Utils.getMD5(loginRequest.getPassword()));
    }

    private void initUserInfo(User user) {
        String userId = user.getId();

        Address address = new Address();
        address.setUserId(userId);

        UserPreference userPreference = new UserPreference();
        userPreference.setUserId(userId);
        userPreference.setOtherUserMessageNotice("1");
        userPreference.setSysMessageNotice("1");
        userPreference.setTodoNotice("1");

        UserTag userTag = new UserTag();
        userTag.setUserId(userId);
        userTag.setId(UUIDUtils.getUUID());

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);

        addressService.save(address);
        userProfileService.save(userProfile);
        userPreferenceService.save(userPreference);
        userTagService.save(userTag);
    }


}
















