package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.TokensRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenControlTest {
    private final TokensRepository tokensRepository = mock(TokensRepository.class);
    private final YandexAuthService yandexAuthService = mock(YandexAuthService.class);
    private final GoogleAuthService googleAuthService = mock(GoogleAuthService.class);

    private final TokenControl tokenControl = new TokenControl(googleAuthService, yandexAuthService, tokensRepository);

    private Faker faker = new Faker();
    private List<TokenEntity> tokens;
    private User user;

    @BeforeEach
    void setUp() {
        user = new EasyRandom().nextObject(User.class);

        TokenEntity tokenEntity1 = new TokenEntity();
        tokenEntity1.setUser(user);
        tokenEntity1.setRefreshToken(faker.chuckNorris().fact());
        tokenEntity1.setAccessToken(faker.animal().name());
        tokenEntity1.setCreatedAt(faker.date().past(12, TimeUnit.DAYS));
        tokenEntity1.setExpiresIn(1233L);
        tokenEntity1.setCloudStorage(CloudStorage.YANDEX_DISK);

        TokenEntity tokenEntity2 = new TokenEntity();
        tokenEntity2.setUser(user);
        tokenEntity2.setRefreshToken(faker.chuckNorris().fact());
        tokenEntity2.setAccessToken(faker.animal().name());
        tokenEntity2.setCreatedAt(faker.date().past(12, TimeUnit.MILLISECONDS));
        tokenEntity2.setExpiresIn(1233L);
        tokenEntity2.setCloudStorage(CloudStorage.YANDEX_DISK);

        TokenEntity tokenEntity3 = new TokenEntity();
        tokenEntity3.setUser(user);
        tokenEntity3.setRefreshToken(faker.chuckNorris().fact());
        tokenEntity3.setAccessToken(faker.animal().name());
        tokenEntity3.setCreatedAt(faker.date().past(12, TimeUnit.MILLISECONDS));
        tokenEntity3.setCloudStorage(CloudStorage.DROPBOX);

        TokenEntity tokenEntity4 = new TokenEntity();
        tokenEntity4.setUser(user);
        tokenEntity4.setRefreshToken(faker.chuckNorris().fact());
        tokenEntity4.setAccessToken(faker.animal().name());
        tokenEntity4.setCreatedAt(faker.date().past(12, TimeUnit.MILLISECONDS));
        tokenEntity2.setExpiresIn(1233L);
        tokenEntity4.setCloudStorage(CloudStorage.GOOGLE_DRIVE);

        tokens = Arrays.asList(tokenEntity1, tokenEntity2, tokenEntity3, tokenEntity4);
    }

    @Test
    @DisplayName("Get tokens test")
    void getTokens() {
        when(tokensRepository.findTokenEntitiesByUser(any(User.class))).thenReturn(tokens);
        Set<TokenEntity> expected = Set.of(tokens.get(1), tokens.get(2), tokens.get(3));

        var result = tokenControl.getTokens(user);

        Assertions.assertAll(
                () -> assertEquals(3, result.size()),
                () -> assertTrue(result.containsAll(expected))
        );
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(CloudStorage.class)
    @DisplayName("Extract tokens test")
    void extractTokens(CloudStorage cloudStorage){
        when(tokensRepository.findTokenEntitiesByUser(any(User.class))).thenReturn(tokens);

        String extractedToken = tokenControl.extractToken(user, cloudStorage);

        Assertions.assertFalse(extractedToken.isEmpty());
    }
}