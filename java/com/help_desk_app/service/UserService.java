package com.help_desk_app.service;

import com.help_desk_app.converter.UserConverter;
import com.help_desk_app.dao.UserDao;
import com.help_desk_app.dto.UserDto;
import com.help_desk_app.entity.User;
import com.help_desk_app.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    private final UserDao userDao;
    private final UserConverter userConverter;

    public UserService(UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    @Transactional
    public UserDto getUserById(Long id) {
        return userConverter.toDto(Optional.ofNullable(userDao.getOne(id)).orElseThrow(() -> new ServiceException("User not found((")));
    }

    @Transactional()
    public UserDto getUser(String email) {
        Optional<User> user = userDao.getUserByEmail(email);
        return userConverter.toDto(user.orElseThrow(() -> new ServiceException("User not found((")));
    }
}
