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
import com.concordia.rpcDomain.response.ArticleResponse;
import com.concordia.rpcDomain.response.UserCenterVOResp;
import com.concordia.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    @Autowired
    private UserTagService userTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;


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

    /**
     * get info of user center
     * @param userId
     * @return
     */
    @Override
    public RespResult getAccountCenterInfo(String userId) {
        UserCenterVOResp userCenterVOResp = new UserCenterVOResp();
        Optional<User> userOptional = userDao.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }

        User user = userOptional.get();
        userCenterVOResp.setUsername(user.getUsername());
        Address address = addressService.findById(userId).get();
        String provinceAndCity = (address.getProvince() == null ? "" : address.getProvince())
                + (address.getCity() == null ? "" : address.getCity());
        userCenterVOResp.setProvinceAndCity(provinceAndCity);
        userCenterVOResp.setPersonalProfile(userProfileService.findById(userId).get()
                .getPersonalProfile());
        userCenterVOResp.setUserTagList(userTagService.getUserTagList(userId));
        List<Article> articles = articleService.getRecentArticles(userId);
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Article article : articles) {
            List<String> tagList = articleTagService.findTagNameByArticleId(article.getId());
            ArticleResponse articleResponse = new ArticleResponse();
            BeanUtils.copyProperties(article, articleResponse);
            articleResponse.setArticleTagList(tagList);
            articleResponseList.add(articleResponse);
        }
        userCenterVOResp.setArticleList(articleResponseList);
        return new RespResult(ResultCode.SUCCESS, userCenterVOResp);
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
















