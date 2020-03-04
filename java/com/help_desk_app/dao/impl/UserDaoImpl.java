package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.UserDao;
import com.help_desk_app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    private static final String SELECT_USER_BY_NAME = "from User WHERE email = :email";
    private static final String NAME_COLUMN = "email";

    private SessionFactory sessionFactory;


    private final PasswordEncoder encoder;

    public UserDaoImpl(SessionFactory sessionFactory, PasswordEncoder encoder) {
        super(sessionFactory);
        super.setAClass(User.class);
        this.sessionFactory = sessionFactory;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user;
        try(Session session = sessionFactory.openSession()) {
            user = Optional.ofNullable((User)session.createQuery(SELECT_USER_BY_NAME).setParameter(NAME_COLUMN, email).uniqueResult());
        }
        return user;
    }
}
