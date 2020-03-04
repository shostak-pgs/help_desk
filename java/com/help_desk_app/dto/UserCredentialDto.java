package com.help_desk_app.dto;

import java.util.Objects;

public class UserCredentialDto {
    private String email;
    private String password;

    public UserCredentialDto(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentialDto userDto = (UserCredentialDto) o;
        return Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "UserCredentialDto{" +
                ", email='" + email + '\'' +
                ", password='" + "******" + '\'' +
                '}';
    }
}
