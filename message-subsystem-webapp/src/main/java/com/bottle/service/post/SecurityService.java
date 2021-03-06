package com.bottle.service.post;

import com.bottle.model.entity.Security;
import com.bottle.model.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class SecurityService {

    private SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Transactional
    public List<Security> getSecurities() {
        return securityRepository.findAll();
    }
}
