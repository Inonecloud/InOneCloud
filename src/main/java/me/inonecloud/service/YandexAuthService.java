package me.inonecloud.service;

import me.inonecloud.clouds.YandexDiskIntegrationAPI;
import me.inonecloud.clouds.dto.YandexAccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class YandexAuthService implements CloudsAuthService {
    private final YandexDiskIntegrationAPI yandexDiskIntegrationAPI;

    public YandexAuthService(YandexDiskIntegrationAPI yandexDiskIntegrationAPI) {
        this.yandexDiskIntegrationAPI = yandexDiskIntegrationAPI;
    }


    @Override
    public void getOAuthToken(String code) {
        ResponseEntity<YandexAccessToken> response = yandexDiskIntegrationAPI.getToken(code);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null) {
            YandexAccessToken token = response.getBody();
        }

    }

    @Override
    public void refreshToken() {
        String refreshToken = "";
        ResponseEntity<YandexAccessToken> response = yandexDiskIntegrationAPI.refreshToken(refreshToken);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null){
            YandexAccessToken token = response.getBody();
        }
    }

    @Override
    public void getCode(Integer code) {
        if (code != null) {
            getOAuthToken(code.toString());
        }
    }

}
