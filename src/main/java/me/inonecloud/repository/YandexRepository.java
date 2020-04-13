package me.inonecloud.repository;

import me.inonecloud.clouds.dto.YandexAboutDisk;
import me.inonecloud.clouds.dto.YandexAccessToken;
import org.springframework.http.ResponseEntity;

public interface YandexRepository {
    public ResponseEntity<YandexAccessToken> getToken(String code);
    public ResponseEntity<YandexAccessToken> refreshToken(String refreshToken);
    public ResponseEntity<YandexAboutDisk> getStorageSpace(String token);
}
