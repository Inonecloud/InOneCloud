package me.inonecloud.service;

import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static me.inonecloud.domain.CloudStorage.*;

@Component
public class TokenControl {
    private final DropboxAuthService dropboxAuthService;
    private final GoogleAuthService googleAuthService;
    private final YandexAuthService yandexAuthService;
    private final TokensRepository tokensRepository;

    public TokenControl(DropboxAuthService dropboxAuthService, GoogleAuthService googleAuthService, YandexAuthService yandexAuthService, TokensRepository tokensRepository) {
        this.dropboxAuthService = dropboxAuthService;
        this.googleAuthService = googleAuthService;
        this.yandexAuthService = yandexAuthService;
        this.tokensRepository = tokensRepository;
    }

    public List<TokenEntity> getTokens(User user) {
        final List<TokenEntity> tokens = new ArrayList<>();
        tokensRepository.findTokenEntitiesByUser(user).stream()
                .collect(Collectors.groupingBy(TokenEntity::getCloudStorage))
                .forEach(
                        (cloudStorage, tokenEntities) -> {
                            if (DROPBOX.equals(cloudStorage))
                                tokens.add(getTokenEntity(tokenEntities, null, user));
                            if (YANDEX_DISK.equals(cloudStorage))
                                tokens.add(getTokenEntity(tokenEntities, yandexAuthService, user));
                            if (GOOGLE_DRIVE.equals(cloudStorage))
                                tokens.add(getTokenEntity(tokenEntities, googleAuthService, user));
                        }
                );

        return tokens.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private TokenEntity getTokenEntity(List<TokenEntity> tokens, CloudsAuthService cloudsAuthService, User user) {
        return tokens.stream()
                .filter(Objects::nonNull)
                .filter(Predicate.not(TokenEntity::isExpired))
                .findFirst()
                .orElseGet(() -> cloudsAuthService.refreshToken(user));
    }
}
