package com.bottle.service.post;


import com.bottle.model.entity.User;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save( user );
    }

    @Transactional
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUser(UUID user_id) {
        return userRepository.findById( user_id );
    }

    @Transactional
    public User getUserByName(String name) {
        return userRepository.findByName( name );
    }

    @Transactional
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
