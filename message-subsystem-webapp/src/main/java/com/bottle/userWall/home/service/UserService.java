package com.bottle.userWall.home.service;


import com.bottle.userWall.home.entity.User;
import com.bottle.userWall.home.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public void addUser(User user) {
        usersRepository.save(user );
    }

    @Transactional
    public Iterable<User> getUsers() {return usersRepository.findAll();}

    @Transactional
    public User getUser(UUID user_id){
        return usersRepository.findById(user_id);
    }

    @Transactional
    public User getUserByName (String name){
        return usersRepository.findByUsername(name);
    }

    @Transactional
    public Iterable<User> findAll() {return usersRepository.findAll();}

}
