package me.inonecloud.repository;

import me.inonecloud.clouds.dto.yandex.YandexAboutDisk;
import me.inonecloud.clouds.dto.yandex.YandexAccessToken;
import me.inonecloud.clouds.dto.yandex.YandexDownloadFile;
import me.inonecloud.clouds.dto.yandex.YandexFilesList;
import org.springframework.http.ResponseEntity;

public interface YandexRepository {
    public ResponseEntity<YandexAccessToken> getToken(String code);
    public ResponseEntity<YandexAccessToken> refreshToken(String refreshToken);
    public ResponseEntity<YandexAboutDisk> getSpaceUsage(String token);
    public ResponseEntity<YandexFilesList> getFiles(String token);
    public ResponseEntity<YandexDownloadFile> downloadFile (String token, String path);
}
