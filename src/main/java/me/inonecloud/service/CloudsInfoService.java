package me.inonecloud.service;

import me.inonecloud.clouds.dto.yandex.YandexAboutDisk;
import me.inonecloud.clouds.dto.dropbox.SpaceInfo;
import me.inonecloud.clouds.dto.google.GoogleSpaceInfo;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.*;
import me.inonecloud.service.dto.CloudInfoDto;
import me.inonecloud.service.dto.CloudsInfoDto;
import me.inonecloud.service.mapper.CloudInfoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CloudsInfoService {
    private final UserRepository userRepository;
    private final TokensRepository tokensRepository;
    private final YandexRepository yandexRepository;
    private final DropboxRepository dropboxRepository;
    private final GoogleDriveRepository googleDriveRepository;
    private final CloudInfoMapper cloudInfoMapper;

    public CloudsInfoService(UserRepository userRepository, TokensRepository tokensRepository, YandexRepository yandexRepository, DropboxRepository dropboxRepository, GoogleDriveRepository googleDriveRepository, CloudInfoMapper cloudInfoMapper) {
        this.userRepository = userRepository;
        this.tokensRepository = tokensRepository;
        this.yandexRepository = yandexRepository;
        this.dropboxRepository = dropboxRepository;
        this.googleDriveRepository = googleDriveRepository;
        this.cloudInfoMapper = cloudInfoMapper;
    }

    public CloudsInfoDto getCloudsInfo(String name) {
        CloudsInfoDto cloudsInfoDto = new CloudsInfoDto();
        User user = userRepository.findByUsername(name);
        List<TokenEntity> tokens = tokensRepository.findTokenEntitiesByUser(user).stream()
                .filter(Predicate.not(TokenEntity::isExpired))
                .collect(Collectors.toList());
        cloudsInfoDto.setYandexDiskInfo(getAboutDiskInfo(extractAccessToken(tokens, CloudStorage.YANDEX_DISK)));
        cloudsInfoDto.setDropboxInfo(getAboutDropboxInfo(extractAccessToken(tokens, CloudStorage.DROPBOX)));
        cloudsInfoDto.setGoogeDriveInfo(getAboutGoogleDriveInfo(extractAccessToken(tokens, CloudStorage.GOOGLE_DRIVE)));
        return cloudsInfoDto;
    }

    public Boolean checkTokens(String username) {
        User user = userRepository.findByUsername(username);
        var tokens = user.getTokens();
        if (tokens == null || tokens.isEmpty()) {
            return false;
        }
        return tokens.stream()
                .filter(Predicate.not(TokenEntity::isExpired))
                .anyMatch(Objects::nonNull);
    }


    private CloudInfoDto getAboutDiskInfo(String token) {
        if (token == null){
            return null;
        }

        ResponseEntity<YandexAboutDisk> storageSpaceResponse = yandexRepository.getStorageSpace(token);
        if (storageSpaceResponse.getStatusCode().is2xxSuccessful()) {
            return cloudInfoMapper.yandexToCloudDto(storageSpaceResponse.getBody());
        }

        return new CloudInfoDto();
    }

    private CloudInfoDto getAboutDropboxInfo(String token) {
        if (token == null) {
            return new CloudInfoDto();
        }

        ResponseEntity<SpaceInfo> spaceUsageResponse = dropboxRepository.getSpaceUsage(token);
        if (spaceUsageResponse.getStatusCode().is2xxSuccessful()) {
            return cloudInfoMapper.dropboxToCloudDto(spaceUsageResponse.getBody());
        }
        return new CloudInfoDto();
    }

    private CloudInfoDto getAboutGoogleDriveInfo(String token) {
        if (token == null) {
            return new CloudInfoDto();
        }

        ResponseEntity<GoogleSpaceInfo> driveSpaceResponse = googleDriveRepository.getStorageSpace(token);
        if (driveSpaceResponse.getStatusCode().is2xxSuccessful()) {
            return cloudInfoMapper.googleDriveToCloudDto(driveSpaceResponse.getBody());
        }
        return new CloudInfoDto();
    }

    private String extractAccessToken( List<TokenEntity> tokenEntities, CloudStorage cloudStorage){
        return tokenEntities.stream()
                .filter(tokenEntity -> tokenEntity.getCloudStorage().equals(cloudStorage))
                .findFirst()
                .map(TokenEntity::getAccessToken)
                .orElse(null);
    }
}
