package com.concordia.component.validate;

import com.concordia.component.exception.ValidateException;
import org.springframework.stereotype.Service;

@Service
public interface FormValidator <T> {

    boolean canAccept(T arg);

    void validate(T arg) throws ValidateException;
}
