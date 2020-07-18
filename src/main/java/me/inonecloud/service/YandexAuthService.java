package me.inonecloud.service;

import me.inonecloud.clouds.YandexDiskIntegrationAPI;
import me.inonecloud.clouds.dto.yandex.YandexAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.repository.YandexRepository;
import me.inonecloud.security.SecurityUtils;
import me.inonecloud.service.mapper.YandexTokenMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class YandexAuthService implements CloudsAuthService {
    private final YandexRepository yandexRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final YandexTokenMapper tokenMapper;

    public YandexAuthService(YandexDiskIntegrationAPI yandexRepository, TokensRepository tokensRepository, UserRepository userRepository, YandexTokenMapper tokenMapper) {
        this.yandexRepository = yandexRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
        this.tokenMapper = tokenMapper;
    }

    @Override
    public void getOAuthToken(String code) {
        ResponseEntity<YandexAccessToken> response = yandexRepository.getToken(code);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null) {
            TokenEntity yandexToken = tokenMapper.toEntity(response.getBody());
            yandexToken.setUser(SecurityUtils.getCurrentUserLogin()
                    .flatMap(userRepository::findByUsername)
                    .orElseThrow());
            tokensRepository.save(yandexToken);
        }
    }

    @Override
    public TokenEntity refreshToken(User user) {
        TokenEntity oldToken = tokensRepository.findTokenEntitiesByUserAndCloudStorage(user, CloudStorage.YANDEX_DISK);
        String refreshToken = oldToken.getRefreshToken();
        ResponseEntity<YandexAccessToken> response = yandexRepository.refreshToken(refreshToken);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null) {
            TokenEntity newToken = tokenMapper.toEntity(response.getBody());
            newToken.setUser(oldToken.getUser());
            newToken.setId(oldToken.getId());
            tokensRepository.save(newToken);
            return newToken;
        }
        return null;
    }

    @Override
    public void getCode(String code) {
        if (code != null) {
            getOAuthToken(code);
        }
    }

}