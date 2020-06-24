package me.inonecloud.repository;

import me.inonecloud.clouds.dto.google.GetFilesGoogleDrive;
import me.inonecloud.clouds.dto.google.GoogleAccessToken;
import me.inonecloud.clouds.dto.google.GoogleSpaceInfo;
import org.springframework.http.ResponseEntity;

public interface GoogleDriveRepository {
    public ResponseEntity<GoogleAccessToken> getToken(String code);

    public ResponseEntity<GoogleAccessToken> refreshToken(String refreshToken);

    public ResponseEntity<GoogleSpaceInfo> getStorageSpace(String token);

    public ResponseEntity<GetFilesGoogleDrive> getFiles(String token);
}
