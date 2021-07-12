package com.concordia.component.validate;

import com.concordia.component.exception.ValidateException;
import org.springframework.stereotype.Service;

@Service
public interface ReqValidateManager<T> {

    void doExecute(T arg) throws ValidateException;
}
