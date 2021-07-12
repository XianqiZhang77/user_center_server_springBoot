package com.concordia.component.validate;

import com.concordia.component.exception.ValidateException;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.RegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RegisterFormValidator implements FormValidator<RegisterRequest> {
    @Override
    public boolean canAccept(RegisterRequest arg) {
        if (arg instanceof RegisterRequest) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(RegisterRequest arg) throws ValidateException{
        if (StringUtils.isBlank(arg.getUsername())
                || StringUtils.isBlank(arg.getPassword())
                || StringUtils.isBlank(arg.getEmail())) {
            throw new ValidateException(ResultCode.REGISTER_DATA_IS_WRONG
                    , "Form validation fails, please check your input");
        }
    }
}
