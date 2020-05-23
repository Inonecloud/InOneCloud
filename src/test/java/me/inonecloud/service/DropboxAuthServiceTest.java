package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.clouds.dto.dropbox.DropboxAccessToken;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.DropboxRepository;
import me.inonecloud.repository.TokensRepository;
import me.inonecloud.repository.UserRepository;
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

class DropboxAuthServiceTest {
    private final DropboxRepository dropboxRepository = Mockito.mock(DropboxRepository.class);
    private final TokensRepository tokensRepository = Mockito.mock(TokensRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final Faker faker= new Faker();

    private final CloudsAuthService dropboxAuthService = new DropboxAuthService(dropboxRepository, tokensRepository, userRepository);
    private DropboxAccessToken dropboxAccessToken;

    @BeforeEach
    void setUp() {
        dropboxAccessToken = new EasyRandom().nextObject(DropboxAccessToken.class);

        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(tokensRepository.save(any(TokenEntity.class))).thenReturn(new TokenEntity());
    }

    @Test
    void getOAuthToken() {
        when(dropboxRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(dropboxAccessToken, HttpStatus.OK));

        dropboxAuthService.getOAuthToken(faker.code().asin(), faker.funnyName().name());

        verify(dropboxRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    void getOAuthToken_BadResponse() {
        when(dropboxRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(dropboxAccessToken, HttpStatus.BAD_GATEWAY));

        dropboxAuthService.getOAuthToken(faker.code().asin(), faker.funnyName().name());

        verify(dropboxRepository, times(1)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }

    @Test
    void getCode() {
        when(dropboxRepository.getToken(anyString())).thenReturn(new ResponseEntity<>(dropboxAccessToken, HttpStatus.OK));

        dropboxAuthService.getCode(faker.code().asin(), faker.funnyName().name());

        verify(dropboxRepository, times(1)).getToken(anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(tokensRepository, times(1)).save(any(TokenEntity.class));
    }

    @Test
    void getCode_nullableCode() {

        dropboxAuthService.getCode(null, faker.funnyName().name());

        verify(dropboxRepository, times(0)).getToken(anyString());
        verify(userRepository, times(0)).findByUsername(anyString());
        verify(tokensRepository, times(0)).save(any(TokenEntity.class));
    }
}