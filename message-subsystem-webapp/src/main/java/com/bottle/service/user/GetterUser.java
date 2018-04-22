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
            String name = "NotUser";
            String surname = "NotUser";
            String avatar = "";
            return createUser(id, name, surname, avatar); //TODO: добавить поля из запроса
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    private User createUser(UUID id, String name, String surname, String avatar) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setAvatarUrl(avatar);
        userRepository.save(user);
        return user;
    }
}
