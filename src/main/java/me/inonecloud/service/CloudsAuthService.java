package me.inonecloud.service;

import org.springframework.stereotype.Service;

@Service
public interface CloudsAuthService {
    public void getOAuthToken(String code, String name);
    public void refreshToken();
    public void getCode(String code, String name);
}
