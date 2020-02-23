package me.inonecloud.service.mapper;

import me.inonecloud.clouds.dto.YandexAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TokenMapper implements EntityMapper<YandexAccessToken, TokenEntity> {
    @Override
    public TokenEntity toEntity(YandexAccessToken dto) {
        TokenEntity token = new TokenEntity();
        token.setAccessToken(dto.getAccessToken());
        token.setRefreshToken(dto.getRefreshToken());
        token.setExpiresIn(dto.getExpiresIn());
        token.setCreatedAt(new Date());
        token.setCloudStorage(CloudStorage.YANDEX_DISK);
        return token;
    }

    @Override
    public YandexAccessToken toDto(TokenEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TokenEntity> toEntity(List<YandexAccessToken> dtoList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<YandexAccessToken> toDto(List<TokenEntity> entityList) {
        throw new UnsupportedOperationException();
    }
}
