package com.bottle.userWall.home.service;


import com.bottle.userWall.home.entity.Security;
import com.bottle.userWall.home.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class SecurityService {
    @Autowired
    SecurityRepository securityRepository;

    @Transactional
    public Iterable<Security> getSecurities() {return  securityRepository.findAll();}

    @Transactional
    public Security getSecurity(Integer security_id) {
        return securityRepository.findOne(security_id);
    }

    @Transactional
    public Security getSecurityByName (String name){
        return securityRepository.findByName(name);
    }
}
