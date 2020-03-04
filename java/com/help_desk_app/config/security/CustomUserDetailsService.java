package com.help_desk_app.config.security;

import com.help_desk_app.dao.impl.UserDaoImpl;
import com.help_desk_app.entity.User;
import com.help_desk_app.exception.UserNameNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class CustomUserDetailsService implements UserDetailsService{
    private UserDaoImpl dao;

    public CustomUserDetailsService(UserDaoImpl dao){
        this.dao=dao;
    }

    @Override
    public CustomUserPrincipal loadUserByUsername(final String email) throws UserNameNotFoundException {
        final User user = dao.getUserByEmail(email)
                        .orElseThrow(() -> new UserNameNotFoundException("User with email = " + email + " not found"));
        return new CustomUserPrincipal(user);
    }
}
