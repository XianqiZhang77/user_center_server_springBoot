package com.concordia.component.validate;

import com.concordia.component.exception.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReqValidateManagerImpl<T> implements ReqValidateManager<T> {

    @Autowired
    private List<FormValidator> validators;

    @Override
    public void doExecute(T arg) throws ValidateException {
        for (FormValidator validator : validators) {

            if (validator.canAccept(arg)) {
                validator.validate(arg);
            }

        }
    }
}
