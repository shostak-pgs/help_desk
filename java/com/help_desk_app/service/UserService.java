package com.help_desk_app.service;

import com.help_desk_app.dao.UserDao;
import com.help_desk_app.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User getUserById(Long id) {
        return userDao.getOne(id);
    }
}
