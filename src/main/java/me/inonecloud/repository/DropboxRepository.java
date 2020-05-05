package me.inonecloud.repository;

import me.inonecloud.clouds.dto.dropbox.DropboxAccessToken;
import me.inonecloud.clouds.dto.dropbox.SpaceInfo;
import org.springframework.http.ResponseEntity;

public interface DropboxRepository {
    public ResponseEntity<DropboxAccessToken> getToken(String code);
    public ResponseEntity<SpaceInfo> getSpaceUsage(String token);
}
