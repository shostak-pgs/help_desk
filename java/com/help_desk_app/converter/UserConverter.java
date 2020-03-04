package com.help_desk_app.converter;

import com.help_desk_app.dto.UserDto;
import com.help_desk_app.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final PasswordEncoder encoder;

    public UserConverter(final PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public UserDto toDto(final User entity) {
        return new UserDto(entity.getId(), entity.getFirstName(), entity.getLastName(),
                 entity.getRole(), entity.getEmail(), entity.getPassword());
    }

    public User toEntity(final UserDto dto) {
        return new User(dto.getFirstName(), dto.getLastName(),
                dto.getRole(), dto.getEmail(), encoder.encode(dto.getPassword()));
    }
}
