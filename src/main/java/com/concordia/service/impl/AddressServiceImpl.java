package com.concordia.service.impl;

import com.concordia.dao.AddressDao;
import com.concordia.pojo.Address;
import com.concordia.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, String>
        implements AddressService {


    @Autowired
    private AddressDao addressDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return addressDao;
    }
}
