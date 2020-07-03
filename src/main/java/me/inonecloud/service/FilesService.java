package me.inonecloud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.inonecloud.clouds.dto.dropbox.GetFilesDropboxRs;
import me.inonecloud.clouds.dto.google.GetFilesGoogleDrive;
import me.inonecloud.clouds.dto.google.GoogleFile;
import me.inonecloud.clouds.dto.yandex.Item;
import me.inonecloud.clouds.dto.yandex.YandexFilesList;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import me.inonecloud.repository.*;
import me.inonecloud.service.dto.FilesListDto;
import me.inonecloud.service.mapper.FilesListMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FilesService {
    private final DropboxRepository dropboxRepository;
    private final GoogleDriveRepository googleDriveRepository;
    private final YandexRepository yandexRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final FilesListMapper filesListMapper;

    public FilesService(DropboxRepository dropboxRepository, GoogleDriveRepository googleDriveRepository, YandexRepository yandexRepository, TokensRepository tokensRepository, UserRepository userRepository, FilesListMapper filesListMapper) {
        this.dropboxRepository = dropboxRepository;
        this.googleDriveRepository = googleDriveRepository;
        this.yandexRepository = yandexRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
        this.filesListMapper = filesListMapper;
    }


    public FilesListDto getDropboxFilesList(String name) {
        User user = userRepository.findByUsername(name);
        var tokens = getTokens(user);

        String token = extractToken(tokens, CloudStorage.DROPBOX);

        ResponseEntity<GetFilesDropboxRs> response = dropboxRepository.getFiles(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            var files = response.getBody().getEntries();
            while (response.getBody().getHasMore()) {
                try {
                    response = dropboxRepository.getFilesNext(token, response.getBody().getCursor());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                files.addAll(response.getBody().getEntries());
            }
            return filesListMapper.dropboxFilesToInOneCloudFiles(files);
            //Todo convert files list to dto
        }
        return null;
    }

    public FilesListDto getYandexFilesList(String name) {
        User user = userRepository.findByUsername(name);
        var tokens = getTokens(user);

        String token = extractToken(tokens, CloudStorage.YANDEX_DISK);

        ResponseEntity<YandexFilesList> response = yandexRepository.getFiles(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Item> files = response.getBody().getItems();
            return filesListMapper.yandexFilesToInOneCloudFiles(files);
        }
        //Todo convert files list to dto
        return null;
    }

    public FilesListDto getGoogleFilesList(String name) {
        User user = userRepository.findByUsername(name);
        var tokens = getTokens(user);

        String token = extractToken(tokens, CloudStorage.GOOGLE_DRIVE);

        if (token == null) {
            return null; //Todo change to exeption or something else
        }

        ResponseEntity<GetFilesGoogleDrive> response = googleDriveRepository.getFiles(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<GoogleFile> files = response.getBody().getFiles();
            return filesListMapper.googleFilesToInOneCloudFiles(files);
        }


        //Todo convert files list to dto
        return null;
    }


    private String extractToken(List<TokenEntity> tokens, CloudStorage storageType) {
        return tokens.stream()
                .filter(tokenEntity -> storageType.equals(tokenEntity.getCloudStorage()))
                .findFirst()
                .map(TokenEntity::getAccessToken)
                .orElse(null);
    }

    private List<TokenEntity> getTokens(User user) {
        return tokensRepository.findTokenEntitiesByUser(user).stream()
                .filter(Predicate.not(TokenEntity::isExpired))
                .collect(Collectors.toList());
    }
}
