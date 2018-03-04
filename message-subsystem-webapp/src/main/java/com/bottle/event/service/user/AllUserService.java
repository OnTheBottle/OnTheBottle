package com.bottle.event.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AllUserService {
    private RegistrationUser registrationUser;
    private GetterUser getterUser;

    @Autowired
    public AllUserService(RegistrationUser registrationUser, GetterUser getterUser) {
        this.registrationUser = registrationUser;
        this.getterUser = getterUser;
    }

    public String registrationUser() {
        return registrationUser.create();
    }

    public List<UUID> getAllUsers() {
        return getterUser.getAllId();
    }
}
