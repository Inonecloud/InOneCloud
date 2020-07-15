package me.inonecloud.service;

import me.inonecloud.clouds.dto.google.GoogleAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.GoogleDriveRepository;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.service.mapper.GoogleTokenMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService implements CloudsAuthService {

    private final GoogleDriveRepository googleDriveRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final GoogleTokenMapper tokenMapper;

    public GoogleAuthService(GoogleDriveRepository googleDriveRepository, TokensRepository tokensRepository, UserRepository userRepository, GoogleTokenMapper tokenMapper) {
        this.googleDriveRepository = googleDriveRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
        this.tokenMapper = tokenMapper;
    }

    @Override
    public void getOAuthToken(String code, String name) {
        ResponseEntity<GoogleAccessToken> response = googleDriveRepository.getToken(code);
        if (response.getStatusCode().is2xxSuccessful()) {
            TokenEntity tokenEntity = tokenMapper.toEntity(response.getBody());
            tokenEntity.setUser(userRepository.findByUsername(name));
            tokensRepository.save(tokenEntity);
        }
    }

    @Override
    public TokenEntity refreshToken(User user) {
        TokenEntity googleLastToken = user.getTokens().stream()
                .filter(tokenEntity -> CloudStorage.GOOGLE_DRIVE.equals(tokenEntity.getCloudStorage()))
                .reduce((first, second) -> second)
                .orElse(null);

        ResponseEntity<GoogleAccessToken> response = googleDriveRepository.refreshToken(googleLastToken.getRefreshToken());
        if (response.getStatusCode().is2xxSuccessful()) {
            TokenEntity newToken = tokenMapper.toEntity(response.getBody());
            newToken.setRefreshToken(googleLastToken.getRefreshToken());
            newToken.setUser(user);
            tokensRepository.save(newToken);
            return newToken;
        }
        return null;
    }

    @Override
    public void getCode(String code, String name) {
        if (code != null) {
            this.getOAuthToken(code, name);
        }
    }
}
