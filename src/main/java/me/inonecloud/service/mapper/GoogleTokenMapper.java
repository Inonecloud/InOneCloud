package me.inonecloud.service.mapper;

import me.inonecloud.clouds.dto.google.GoogleAccessToken;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoogleTokenMapper implements EntityMapper<GoogleAccessToken, TokenEntity> {
    @Override
    public TokenEntity toEntity(GoogleAccessToken dto) {
        TokenEntity entity = new TokenEntity();
        entity.setAccessToken(dto.getAccessToken());
        entity.setExpiresIn(dto.getExpiredIn());
        entity.setCreatedAt(new Date());
        entity.setRefreshToken(dto.getRefreshToken());
        entity.setCloudStorage(CloudStorage.GOOGLE_DRIVE);
        return entity;
    }

    @Override
    public GoogleAccessToken toDto(TokenEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TokenEntity> toEntity(List<GoogleAccessToken> dtoList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GoogleAccessToken> toDto(List<TokenEntity> entityList) {
        throw new UnsupportedOperationException();
    }
}
