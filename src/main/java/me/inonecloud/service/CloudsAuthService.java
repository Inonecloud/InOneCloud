package me.inonecloud.service;

import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public interface CloudsAuthService {
    public void getOAuthToken(String code, String name);
    public TokenEntity refreshToken(User user);
    public void getCode(String code, String name);
}
