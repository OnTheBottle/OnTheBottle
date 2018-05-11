package com.bottle.service.user;

import com.bottle.model.entity.User;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetterUser {
    private UserRepository userRepository;

    @Autowired
    public GetterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(UUID id) {
        if (userRepository.exists(id)) {
            return userRepository.getOne(id);
        } else {
            return createUser(id);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean isExistUserById(UUID id) {
        return userRepository.exists(id);
    }

    private User createUser(UUID id) {
        User user = new User();
        user.setId(id);
        userRepository.save(user);
        return user;
    }
}
