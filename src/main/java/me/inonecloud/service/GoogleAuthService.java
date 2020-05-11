package me.inonecloud.service;

import me.inonecloud.clouds.dto.google.GoogleAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.repository.GoogleDriveRepository;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GoogleAuthService implements CloudsAuthService {

    private final GoogleDriveRepository googleDriveRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;

    public GoogleAuthService(GoogleDriveRepository googleDriveRepository, TokensRepository tokensRepository, UserRepository userRepository) {
        this.googleDriveRepository = googleDriveRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void getOAuthToken(String code, String name) {
        ResponseEntity<GoogleAccessToken> response = googleDriveRepository.getToken(code);
        if(response.getStatusCode().is2xxSuccessful()){
            GoogleAccessToken body = response.getBody();
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setAccessToken(body.getAccessToken());
            tokenEntity.setExpiresIn(body.getExpiredIn());
            tokenEntity.setCreatedAt(new Date());
            tokenEntity.setRefreshToken(body.getRefreshToken());
            tokenEntity.setCloudStorage(CloudStorage.GOOGLE_DRIVE);
            tokenEntity.setUser(userRepository.findByUsername(name));
            tokensRepository.save(tokenEntity);
        }
    }

    @Override
    public void refreshToken() {

    }

    @Override
    public void getCode(String code, String name) {
        this.getOAuthToken(code, name);
    }
}
