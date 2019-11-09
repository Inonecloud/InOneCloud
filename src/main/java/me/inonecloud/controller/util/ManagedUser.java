package me.inonecloud.controller.util;

import me.inonecloud.service.dto.UserDto;

import javax.validation.constraints.Size;

public class ManagedUser extends UserDto {

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 64;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
