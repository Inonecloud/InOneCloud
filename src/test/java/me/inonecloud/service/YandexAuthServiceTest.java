package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.clouds.YandexDiskIntegrationAPI;
import me.inonecloud.clouds.dto.yandex.YandexAccessToken;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.repository.YandexRepository;
import me.inonecloud.service.mapper.YandexTokenMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class YandexAuthServiceTest {
    private final Authentication authentication = Mockito.mock(Authentication.class);
    private final SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    private final YandexRepository yandexRepository = Mockito.mock(YandexDiskIntegrationAPI.class);
    private final TokensRepository tokensRepository = Mockito.mock(TokensRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final YandexTokenMapper tokenMapper = new YandexTokenMapper();

    private final YandexAuthService yandexAuthService= new YandexAuthService((YandexDiskIntegrationAPI) yandexRepository, tokensRepository,userRepository, tokenMapper);

    private YandexAccessToken yandexAccessToken;
    private final Faker faker= new Faker();

    @BeforeEach
    void setUp() {
        yandexAccessToken = new EasyRandom(new EasyRandomParameters()
            .excludeField(FieldPredicates.named("errorDescription"))
            .excludeField(FieldPredicates.named("error"))
        ).nextObject(YandexAccessToken.class);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));
        when(tokensRepository.save(any(TokenEntity.class))).thenReturn(new TokenEntity());
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .thenReturn(faker.funnyName().name());
    }


    @Test
    @DisplayName("Get OAuth Yandex Disk token test")
    void getOAuthToken() {
        when(yandexRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(yandexAccessToken, HttpStatus.OK));

        yandexAuthService.getOAuthToken(faker.code().asin());

        verify(yandexRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    @DisplayName("Get OAuth Yandex Disk token test with bad response error")
    void getOAuthToken_BadResponse() {
        when(yandexRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(yandexAccessToken, HttpStatus.BAD_GATEWAY));

        yandexAuthService.getOAuthToken(faker.code().asin());

        verify(yandexRepository, times(1)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }

    @Test
    @DisplayName("Refresh Yandex Disk token test")
    @Disabled("Not implemented")
    void refreshToken() {
    }

    @Test
    @DisplayName("Get Yandex Disk code for token exchange test")
    void getCode() {
        when(yandexRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(yandexAccessToken, HttpStatus.OK));

        yandexAuthService.getCode(faker.code().asin());

        verify(yandexRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    @DisplayName("Get Yandex Disk code without code test")
    void getCode_nullableCode() {

        yandexAuthService.getCode(null);

        verify(yandexRepository, times(0)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }
}