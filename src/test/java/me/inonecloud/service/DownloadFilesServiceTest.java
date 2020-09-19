package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.clouds.dto.dropbox.DownloadFileDropboxRs;
import me.inonecloud.clouds.dto.google.DownloadFileGoogle;
import me.inonecloud.clouds.dto.yandex.YandexDownloadFile;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.User;
import me.inonecloud.repository.DropboxRepository;
import me.inonecloud.repository.GoogleDriveRepository;
import me.inonecloud.repository.YandexRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class DownloadFilesServiceTest {
    private final GoogleDriveRepository googleDriveRepository = Mockito.mock(GoogleDriveRepository.class);
    private final DropboxRepository dropboxRepository = Mockito.mock(DropboxRepository.class);
    private final YandexRepository yandexRepository = Mockito.mock(YandexRepository.class);

    private final UserService userService = Mockito.mock(UserService.class);
    private final TokenControl tokenControl = Mockito.mock(TokenControl.class);

    private final Faker faker = new Faker();
    private DownloadFilesService downloadFilesService = new DownloadFilesService(dropboxRepository,
                                                                                googleDriveRepository,
                                                                                yandexRepository,
                                                                                tokenControl,
                                                                                userService);

    @BeforeEach
    void setUp() {
        User user = new EasyRandom().nextObject(User.class);
        when(userService.getCurrentUser()).thenReturn(user);
        when(tokenControl.extractToken(eq(user), any(CloudStorage.class))).thenReturn(faker.crypto().sha1());
    }

    @Test
    @DisplayName("Download file from Dropbox")
    void downloadDropboxFile() {
        DownloadFileDropboxRs downloadFileDropboxRs = new EasyRandom().nextObject(DownloadFileDropboxRs.class);

        when(dropboxRepository.downloadFile(anyString(), anyString())).thenReturn(ResponseEntity.of(Optional.of(downloadFileDropboxRs)));

        String result = downloadFilesService.downloadDropboxFile("some/path");

        Assertions.assertAll(
                () -> assertEquals(downloadFileDropboxRs.getLink(), result)
        );

    }

    @Test
    @DisplayName("Download file from Yandex disk")
    void downloadYandexFile() {
        YandexDownloadFile yandexDownloadFile = new EasyRandom().nextObject(YandexDownloadFile.class);

        when(yandexRepository.downloadFile(anyString(), anyString())).thenReturn(ResponseEntity.of(Optional.of(yandexDownloadFile)));

        String result = downloadFilesService.downloadYandexFile("some/path");
        Assertions.assertAll(
                () -> assertEquals(yandexDownloadFile.getLink(), result)
        );
    }

    @Test
    @DisplayName("Download file from Google drive")
    void downloadGoogleFile() {
        DownloadFileGoogle downloadFileGoogle = new EasyRandom().nextObject(DownloadFileGoogle.class);

        when(googleDriveRepository.downloadFile(anyString(), anyString())).thenReturn(ResponseEntity.of(Optional.of(downloadFileGoogle)));

        String result = downloadFilesService.downloadGoogleFile("fileId");
        Assertions.assertAll(
                () -> assertEquals(downloadFileGoogle.getWebContentLink(), result)
        );
    }


}