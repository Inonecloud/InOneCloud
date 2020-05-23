package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.clouds.dto.google.GoogleAccessToken;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.GoogleDriveRepository;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GoogleAuthServiceTest {
    private final GoogleDriveRepository googleDriveRepository = Mockito.mock(GoogleDriveRepository.class);
    private final TokensRepository tokensRepository = Mockito.mock(TokensRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final Faker faker= new Faker();

    private final CloudsAuthService googleAuthService = new GoogleAuthService(googleDriveRepository, tokensRepository, userRepository);
    private GoogleAccessToken googleAccessToken;

    @BeforeEach
    void setUp() {
        googleAccessToken = new EasyRandom().nextObject(GoogleAccessToken.class);

        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(tokensRepository.save(any(TokenEntity.class))).thenReturn(new TokenEntity());
    }

    @Test
    void getOAuthToken() {
        when(googleDriveRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(googleAccessToken, HttpStatus.OK));

        googleAuthService.getOAuthToken(faker.code().asin(), faker.funnyName().name());

        verify(googleDriveRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    void getOAuthToken_BadResponse() {
        when(googleDriveRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(googleAccessToken, HttpStatus.BAD_GATEWAY));

        googleAuthService.getOAuthToken(faker.code().asin(), faker.funnyName().name());

        verify(googleDriveRepository, times(1)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }

    @Test
    void getCode() {
        when(googleDriveRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(googleAccessToken, HttpStatus.OK));

        googleAuthService.getCode(faker.code().asin(), faker.funnyName().name());

        verify(googleDriveRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    void getCode_nullableCode() {
        googleAuthService.getCode(null, faker.funnyName().name());

        verify(googleDriveRepository, times(0)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }
}