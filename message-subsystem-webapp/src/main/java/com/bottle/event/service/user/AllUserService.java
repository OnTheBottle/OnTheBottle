package com.bottle.event.service.user;

import com.bottle.event.model.DTO.EventDTO;
import com.bottle.event.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AllUserService {
    private GetterUser getterUser;

    @Autowired
    public AllUserService(GetterUser getterUser) {
        this.getterUser = getterUser;
    }

    public User createOrGet(EventDTO eventDTO) { //TODO
        UUID id = eventDTO.getOwner().equals("0") ?
                UUID.randomUUID() : UUID.fromString(eventDTO.getOwner());
        return getterUser.createOrGet(id);
    }

    public User createOrGet() { //TODO test method
        UUID id = UUID.randomUUID();
        return getterUser.createOrGet(id);
    }

    public List<UUID> getAllUsers() {
        return getterUser.getAllId();
    }
}
