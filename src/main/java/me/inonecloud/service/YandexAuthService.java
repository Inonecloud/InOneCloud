package me.inonecloud.service;

import me.inonecloud.clouds.YandexDiskIntegrationAPI;
import me.inonecloud.clouds.dto.yandex.YandexAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.repository.YandexRepository;
import me.inonecloud.service.mapper.TokenMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class YandexAuthService implements CloudsAuthService {
    private final YandexRepository yandexRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final TokenMapper tokenMapper;

    public YandexAuthService(YandexDiskIntegrationAPI yandexRepository, TokensRepository tokensRepository, UserRepository userRepository, TokenMapper tokenMapper) {
        this.yandexRepository = yandexRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
        this.tokenMapper = tokenMapper;
    }

    @Override
    public void getOAuthToken(String code, String name) {
        ResponseEntity<YandexAccessToken> response = yandexRepository.getToken(code);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null) {
            TokenEntity yandexToken = tokenMapper.toEntity(response.getBody());
            yandexToken.setUser(userRepository.findByUsername(name));
            tokensRepository.save(yandexToken);
        }
    }

    @Override
    public void refreshToken() {
        TokenEntity oldToken = tokensRepository.findTokenEntitiesByUserAndCloudStorage(new User(), CloudStorage.YANDEX_DISK);
        String refreshToken = oldToken.getRefreshToken();
        ResponseEntity<YandexAccessToken> response = yandexRepository.refreshToken(refreshToken);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null) {
            TokenEntity newToken = tokenMapper.toEntity(response.getBody());
            newToken.setUser(oldToken.getUser());
            newToken.setId(oldToken.getId());
            tokensRepository.save(newToken);
        }
    }

    @Override
    public void getCode(String code, String name) {
        if (code != null) {
            getOAuthToken(code, name);
        }
    }

}