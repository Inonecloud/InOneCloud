package me.inonecloud.service;

import org.springframework.stereotype.Service;

@Service
public interface CloudsAuthService {
    public void getOAuthToken(String code);
    public void refreshToken();
    public void getCode(Integer code);
}
