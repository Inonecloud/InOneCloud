package me.inonecloud.service;

import me.inonecloud.clouds.dto.dropbox.DropboxAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.repository.DropboxRepository;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DropboxAuthService implements CloudsAuthService {

    private final DropboxRepository dropboxRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;

    public DropboxAuthService(DropboxRepository dropboxRepository, TokensRepository tokensRepository, UserRepository userRepository) {
        this.dropboxRepository = dropboxRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void getOAuthToken(String code, String name) {
        ResponseEntity<DropboxAccessToken> response = dropboxRepository.getToken(code);
        if (response.getStatusCode() == HttpStatus.OK) {
            DropboxAccessToken body = response.getBody();
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setAccessToken(body.getAccessToken());
            tokenEntity.setCloudStorage(CloudStorage.DROPBOX);
            tokenEntity.setCreatedAt(new Date());
            tokenEntity.setUser(userRepository.findByUsername(name));
            tokensRepository.save(tokenEntity);
        }
    }

    @Override
    public void refreshToken() {

    }

    @Override
    public void getCode(String code, String name) {
        if (code != null){
            this.getOAuthToken(code,name);
        }
    }
}
