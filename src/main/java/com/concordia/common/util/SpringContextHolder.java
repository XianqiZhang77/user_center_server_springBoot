package com.concordia.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        Map<String, T> mapBeans = applicationContext.getBeansOfType(clazz);
        if (mapBeans != null && !mapBeans.isEmpty()) {
            return mapBeans.values().iterator().next();
        } else {
            return null;
        }
    }
    
    private void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext injection failure");
        }
    }

}
