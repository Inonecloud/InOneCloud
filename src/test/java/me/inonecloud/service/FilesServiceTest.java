package me.inonecloud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import me.inonecloud.clouds.dto.dropbox.GetFilesDropboxRs;
import me.inonecloud.clouds.dto.google.GetFilesGoogleDrive;
import me.inonecloud.clouds.dto.yandex.YandexFilesList;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.User;
import me.inonecloud.repository.DropboxRepository;
import me.inonecloud.repository.GoogleDriveRepository;
import me.inonecloud.repository.YandexRepository;
import me.inonecloud.service.dto.FilesListDto;
import me.inonecloud.service.dto.Type;
import me.inonecloud.service.mapper.FilesListMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class FilesServiceTest {

    private final DropboxRepository dropboxRepository = Mockito.mock(DropboxRepository.class);
    private final GoogleDriveRepository googleDriveRepository = Mockito.mock(GoogleDriveRepository.class);
    private final YandexRepository yandexRepository = Mockito.mock(YandexRepository.class);


    private final TokenControl tokenControl = Mockito.mock(TokenControl.class);
    private final UserService userService = Mockito.mock(UserService.class);

    private final FilesListMapper mapper = new FilesListMapper();
    private Faker faker = new Faker();
    private final FilesService filesService = new FilesService(dropboxRepository,
            googleDriveRepository,
            yandexRepository,
            mapper,
            tokenControl,
            userService
    );

    @BeforeEach
    void setUp() {
        User user = new EasyRandom().nextObject(User.class);
        when(userService.getCurrentUser()).thenReturn(user);
        when(tokenControl.extractToken(eq(user), any(CloudStorage.class))).thenReturn(faker.crypto().sha1());
    }

    @Test
    @DisplayName("Get files from Dropbox")
    void getDropboxFilesList() throws JsonProcessingException {
        GetFilesDropboxRs getFilesDropboxRs = new EasyRandom(new EasyRandomParameters()
                .randomize(FieldPredicates.named("clientModified"), () -> "2020-09-19T09:22:54Z")
                .randomize(FieldPredicates.named("serverModified"), () -> "2020-09-20T09:22:54Z")
                .randomize(FieldPredicates.named("size"), () -> String.valueOf(faker.random().nextLong()))
        ).nextObject(GetFilesDropboxRs.class);
        GetFilesDropboxRs getFilesDropboxRsNext = new EasyRandom(new EasyRandomParameters()
                .randomize(FieldPredicates.named("hasMore"), () -> false)
                .randomize(FieldPredicates.named("name"), () -> faker.app().name())
                .randomize(FieldPredicates.named("clientModified"), () -> "2020-09-19T09:22:54Z")
                .randomize(FieldPredicates.named("serverModified"), () -> "2020-09-20T09:22:54Z")
                .randomize(FieldPredicates.named("size"), () -> String.valueOf(faker.random().nextLong()))
        ).nextObject(GetFilesDropboxRs.class);

        when(dropboxRepository.getFiles(anyString())).thenReturn(ResponseEntity.of(Optional.of(getFilesDropboxRs)));
        when(dropboxRepository.getFilesNext(anyString(), eq(getFilesDropboxRs.getCursor()))).thenReturn(ResponseEntity.of(Optional.of(getFilesDropboxRsNext)));

        FilesListDto result = filesService.getDropboxFilesList();

        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(getFilesDropboxRs.getEntries().get(0).getName(), result.getItems().get(0).getName()),
                () -> assertEquals(getFilesDropboxRsNext.getEntries().get(getFilesDropboxRsNext.getEntries().size() - 1).getName(), result.getItems().get(result.getItems().size() - 1).getName()),
                () -> assertEquals(getFilesDropboxRs.getEntries().get(0).getId(), result.getItems().get(0).getId()),
                () -> assertEquals(getFilesDropboxRs.getEntries().get(0).getPathLower(), result.getItems().get(0).getPath()),
                () -> assertEquals(Long.valueOf(getFilesDropboxRs.getEntries().get(0).getSize()), result.getItems().get(0).getSize()),
                () -> assertEquals(CloudStorage.DROPBOX, result.getItems().get(0).getSource()),
                () -> assertEquals(Type.FIlE, result.getItems().get(0).getType())
        );
    }

    @Test
    @DisplayName("Get files from Dropbox JSON Exception")
    void getDropboxFilesList_withException() throws JsonProcessingException {
        GetFilesDropboxRs getFilesDropboxRs = new EasyRandom(new EasyRandomParameters()
                .randomize(FieldPredicates.named("clientModified"), () -> "2020-09-19T09:22:54Z")
                .randomize(FieldPredicates.named("serverModified"), () -> "2020-09-20T09:22:54Z")
                .randomize(FieldPredicates.named("size"), () -> String.valueOf(faker.random().nextLong()))
        ).nextObject(GetFilesDropboxRs.class);

        when(dropboxRepository.getFiles(anyString())).thenReturn(ResponseEntity.of(Optional.of(getFilesDropboxRs)));
        when(dropboxRepository.getFilesNext(anyString(), eq(getFilesDropboxRs.getCursor()))).thenThrow(JsonProcessingException.class);

        FilesListDto result = filesService.getDropboxFilesList();

        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(getFilesDropboxRs.getEntries().size(), result.getItems().size())
        );
    }

    @Test
    @DisplayName("Get files from Dropbox bad response")
    void getDropboxFilesList_badResponse(){
        when(dropboxRepository.getFiles(anyString())).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        Assertions.assertNull(filesService.getDropboxFilesList());
    }

    @Test
    @DisplayName("Get files from Yandex disk")
    void getYandexFilesList() {
        YandexFilesList yandexFilesList = new EasyRandom(new EasyRandomParameters()
                .randomize(FieldPredicates.named("created"), () -> "2020-09-19T09:38:58+00:00")
                .randomize(FieldPredicates.named("modified"), () -> "2020-09-20T09:38:58+00:00")
        ).nextObject(YandexFilesList.class);

        when(yandexRepository.getFiles(anyString())).thenReturn(ResponseEntity.of(Optional.of(yandexFilesList)));

        FilesListDto result = filesService.getYandexFilesList();

        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(yandexFilesList.getItems().get(0).getName(), result.getItems().get(0).getName()),
                () -> assertEquals(yandexFilesList.getItems().get(0).getSize(), result.getItems().get(0).getSize()),
                () -> assertEquals(yandexFilesList.getItems().get(0).getPath(), result.getItems().get(0).getPath()),
                () -> assertEquals(Type.FIlE, result.getItems().get(0).getType()),
                () -> assertEquals(CloudStorage.YANDEX_DISK, result.getItems().get(0).getSource())
        );
    }

    @Test
    @DisplayName("Get files from Yandex disk bad response")
    void getYandexFilesList_badResponse(){
        when(yandexRepository.getFiles(anyString())).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        Assertions.assertNull(filesService.getYandexFilesList());
    }

    @Test
    @DisplayName("Get files from Google drive")
    void getGoogleFilesList() {
        GetFilesGoogleDrive getFilesGoogleDrive = new EasyRandom(new EasyRandomParameters()
                .randomize(FieldPredicates.named("createdTime"), () -> "2020-09-19T12:13:36.092Z")
                .randomize(FieldPredicates.named("modifiedTime"), () -> "2020-09-20T12:13:36.092Z")
                .randomize(FieldPredicates.named("size"), () -> String.valueOf(faker.random().nextLong()))
        ).nextObject(GetFilesGoogleDrive.class);

        when(googleDriveRepository.getFiles(anyString())).thenReturn(ResponseEntity.of(Optional.of(getFilesGoogleDrive)));

        FilesListDto result = filesService.getGoogleFilesList();

        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(getFilesGoogleDrive.getFiles().get(0).getId(), result.getItems().get(0).getId()),
                () -> assertEquals(getFilesGoogleDrive.getFiles().get(0).getName(), result.getItems().get(0).getName()),
                () -> assertEquals(getFilesGoogleDrive.getFiles().get(0).getOriginalFileName(), result.getItems().get(0).getPath()),
                () -> assertEquals(Long.valueOf(getFilesGoogleDrive.getFiles().get(0).getSize()), result.getItems().get(0).getSize()),
                () -> assertEquals(CloudStorage.GOOGLE_DRIVE, result.getItems().get(0).getSource())
        );
    }

    @Test
    @DisplayName("Get files from Google drive bad response")
    void getGoogleFilesList_badResponse(){
        when(googleDriveRepository.getFiles(anyString())).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        Assertions.assertNull(filesService.getGoogleFilesList());
    }
}