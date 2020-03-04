package com.help_desk_app.dao;

import com.help_desk_app.entity.User;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> getUserByEmail(String email);
}
