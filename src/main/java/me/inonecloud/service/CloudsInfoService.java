package me.inonecloud.service;

import me.inonecloud.clouds.dto.YandexAboutDisk;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.repository.YandexRepository;
import me.inonecloud.service.dto.CloudInfoDto;
import me.inonecloud.service.dto.CloudsInfoDto;
import me.inonecloud.service.mapper.CloudInfoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class CloudsInfoService {
    private UserRepository userRepository;
    private TokensRepository tokensRepository;
    private YandexRepository yandexRepository;
    private CloudInfoMapper cloudInfoMapper;

    public CloudsInfoService(UserRepository userRepository, TokensRepository tokensRepository, YandexRepository yandexRepository, CloudInfoMapper cloudInfoMapper) {
        this.userRepository = userRepository;
        this.tokensRepository = tokensRepository;
        this.yandexRepository = yandexRepository;
        this.cloudInfoMapper = cloudInfoMapper;
    }

    public CloudsInfoDto getCloudsInfo(String name){
        CloudsInfoDto cloudsInfoDto = new CloudsInfoDto();
        cloudsInfoDto.setYandexDiskInfo(getAboutDiskInfo(name));

        return cloudsInfoDto;
    }

    public Boolean checkTockens(String username){
        User user = userRepository.findByUsername(username);
        var tokens = user.getTokens();
        if (tokens == null || tokens.isEmpty()){
            return false;
        }
        return tokens.stream()
                .filter(Predicate.not(TokenEntity::isExpired))
                .anyMatch(Objects::nonNull);
    }


    private CloudInfoDto getAboutDiskInfo(String name) {
        User user = userRepository.findByUsername(name);
        TokenEntity tokenEntity = tokensRepository.findTokenEntitiesByUserAndCloudStorage(user, CloudStorage.YANDEX_DISK);
        ResponseEntity<YandexAboutDisk> storageSpaceResponse = yandexRepository.getStorageSpace(tokenEntity.getAccessToken());

        if(storageSpaceResponse.getStatusCode().is2xxSuccessful()){
            return cloudInfoMapper.toCloudDto(storageSpaceResponse.getBody());
        }
        return new CloudInfoDto();
    }
}
