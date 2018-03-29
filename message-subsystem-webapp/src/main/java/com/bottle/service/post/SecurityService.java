package com.bottle.service.post;


import com.bottle.model.entity.Security;
import com.bottle.model.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class SecurityService {

    private SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Transactional
    public Iterable<Security> getSecurities() {
        return securityRepository.findAll();
    }

    @Transactional
    public Security getSecurity(Integer security_id) {
        return securityRepository.findOne( security_id );
    }

    @Transactional
    public Security getSecurityByName(String name) {
        return securityRepository.findByName( name );
    }
}
