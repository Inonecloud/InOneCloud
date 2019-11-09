package me.inonecloud.controller.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUser {

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = ManagedUser.PASSWORD_MIN_LENGTH, max = ManagedUser.PASSWORD_MAX_LENGTH)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
