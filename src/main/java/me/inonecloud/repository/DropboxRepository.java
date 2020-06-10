package me.inonecloud.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.inonecloud.clouds.dto.dropbox.DropboxAccessToken;
import me.inonecloud.clouds.dto.dropbox.GetFilesDropboxRs;
import me.inonecloud.clouds.dto.dropbox.SpaceInfo;
import org.springframework.http.ResponseEntity;

public interface DropboxRepository {
    public ResponseEntity<DropboxAccessToken> getToken(String code);
    public ResponseEntity<SpaceInfo> getSpaceUsage(String token);
    public ResponseEntity<GetFilesDropboxRs> getFiles(String token);
    public ResponseEntity<GetFilesDropboxRs> getFilesNext(String token, String cursor) throws JsonProcessingException;
}
