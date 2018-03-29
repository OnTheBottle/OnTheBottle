package com.bottle.model.repository;

import com.bottle.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class UserStore {
    private UserRepository userRepository;

    @Autowired
    public UserStore(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createOrUpdate(User user) throws SQLException {
        userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) throws SQLException {
        userRepository.delete(id);
    }

    @Transactional
    public User getById(UUID id) throws SQLException {
        return userRepository.getOne(id);
    }

    @Transactional
    public List<User> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Transactional
    public boolean exists(UUID id) throws SQLException {
        return userRepository.exists(id);
    }
}
