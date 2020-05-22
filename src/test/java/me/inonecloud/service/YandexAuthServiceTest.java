package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.clouds.YandexDiskIntegrationAPI;
import me.inonecloud.clouds.dto.YandexAccessToken;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.repository.YandexRepository;
import me.inonecloud.service.mapper.TokenMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class YandexAuthServiceTest {
    private final YandexRepository yandexRepository = Mockito.mock(YandexDiskIntegrationAPI.class);
    private final TokensRepository tokensRepository = Mockito.mock(TokensRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final TokenMapper tokenMapper = new TokenMapper();

    private final YandexAuthService yandexAuthService= new YandexAuthService((YandexDiskIntegrationAPI) yandexRepository, tokensRepository,userRepository, tokenMapper);

    private YandexAccessToken yandexAccessToken;
    private final Faker faker= new Faker();

    @BeforeEach
    void setUp() {
        yandexAccessToken = new EasyRandom(new EasyRandomParameters()
            .excludeField(FieldPredicates.named("errorDescription"))
            .excludeField(FieldPredicates.named("error"))
        ).nextObject(YandexAccessToken.class);

        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(tokensRepository.save(any(TokenEntity.class))).thenReturn(new TokenEntity());
    }


    @Test
    void getOAuthToken() {
        when(yandexRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(yandexAccessToken, HttpStatus.OK));

        yandexAuthService.getOAuthToken(faker.code().asin(), faker.funnyName().name());

        verify(yandexRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    void getOAuthToken_BadResponse() {
        when(yandexRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(yandexAccessToken, HttpStatus.BAD_GATEWAY));

        yandexAuthService.getOAuthToken(faker.code().asin(), faker.funnyName().name());

        verify(yandexRepository, times(1)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }

    @Test
    void refreshToken() {
    }

    @Test
    void getCode() {
        when(yandexRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(yandexAccessToken, HttpStatus.OK));

        yandexAuthService.getCode(faker.code().asin(), faker.funnyName().name());

        verify(yandexRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    void getCode_nullableCode() {

        yandexAuthService.getCode(null, faker.funnyName().name());

        verify(yandexRepository, times(0)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }
}