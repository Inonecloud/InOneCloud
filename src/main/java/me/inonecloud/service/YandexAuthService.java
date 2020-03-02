package me.inonecloud.service;

import me.inonecloud.clouds.YandexDiskIntegrationAPI;
import me.inonecloud.clouds.dto.YandexAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.service.mapper.TokenMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class YandexAuthService implements CloudsAuthService {
    private final YandexDiskIntegrationAPI yandexDiskIntegrationAPI;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final TokenMapper tokenMapper;

    public YandexAuthService(YandexDiskIntegrationAPI yandexDiskIntegrationAPI, TokensRepository tokensRepository, UserRepository userRepository, TokenMapper tokenMapper) {
        this.yandexDiskIntegrationAPI = yandexDiskIntegrationAPI;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
        this.tokenMapper = tokenMapper;
    }


    @Override
    public void getOAuthToken(String code, String name) {
        ResponseEntity<YandexAccessToken> response = yandexDiskIntegrationAPI.getToken(code);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null) {
            TokenEntity yandexToken = tokenMapper.toEntity(response.getBody());
            yandexToken.setUser(userRepository.findByUsername(name)); //FixMe set real user
            tokensRepository.save(yandexToken);
        }
    }

    @Override
    public void refreshToken() {
        TokenEntity oldToken = tokensRepository.findTokenEntitiesByUserAndCloudStorage(new User(), CloudStorage.YANDEX_DISK); //FixMe get real user
        String refreshToken = oldToken.getRefreshToken();
        ResponseEntity<YandexAccessToken> response = yandexDiskIntegrationAPI.refreshToken(refreshToken);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody().getError() == null){
            TokenEntity newToken = tokenMapper.toEntity(response.getBody());
            newToken.setUser(oldToken.getUser());
            newToken.setId(oldToken.getId());
            tokensRepository.save(newToken);
        }
    }

    @Override
    public void getCode(String  code, String name) {
        if (code != null) {
            getOAuthToken(code, name);
        }
    }

}
