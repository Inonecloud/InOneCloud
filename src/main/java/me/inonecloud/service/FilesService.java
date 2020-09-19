package me.inonecloud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.inonecloud.clouds.dto.dropbox.GetFilesDropboxRs;
import me.inonecloud.clouds.dto.google.GetFilesGoogleDrive;
import me.inonecloud.clouds.dto.google.GoogleFile;
import me.inonecloud.clouds.dto.yandex.Item;
import me.inonecloud.clouds.dto.yandex.YandexFilesList;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.repository.*;
import me.inonecloud.service.dto.FilesListDto;
import me.inonecloud.service.mapper.FilesListMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {
    private final DropboxRepository dropboxRepository;
    private final GoogleDriveRepository googleDriveRepository;
    private final YandexRepository yandexRepository;
    private final FilesListMapper filesListMapper;
    private final TokenControl tokenControl;
    private final UserService userService;



    public FilesService(DropboxRepository dropboxRepository, GoogleDriveRepository googleDriveRepository, YandexRepository yandexRepository, FilesListMapper filesListMapper, TokenControl tokenControl, UserService userService) {
        this.dropboxRepository = dropboxRepository;
        this.googleDriveRepository = googleDriveRepository;
        this.yandexRepository = yandexRepository;
        this.filesListMapper = filesListMapper;
        this.tokenControl = tokenControl;
        this.userService = userService;
    }


    public FilesListDto getDropboxFilesList() {
        var user = userService.getCurrentUser();
        var token = tokenControl.extractToken(user, CloudStorage.DROPBOX);

        ResponseEntity<GetFilesDropboxRs> response = dropboxRepository.getFiles(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            var files = response.getBody().getEntries();
            while (response.getBody().getHasMore()) {
                try {
                    response = dropboxRepository.getFilesNext(token, response.getBody().getCursor());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    response.getBody().setHasMore(false);
                }
                files.addAll(response.getBody().getEntries());
            }
            return filesListMapper.dropboxFilesToInOneCloudFiles(files);
            //Todo convert files list to dto
        }
        return null;
    }

    public FilesListDto getYandexFilesList() {
        var user = userService.getCurrentUser();
        var token = tokenControl.extractToken(user, CloudStorage.YANDEX_DISK);

        ResponseEntity<YandexFilesList> response = yandexRepository.getFiles(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Item> files = response.getBody().getItems();
            return filesListMapper.yandexFilesToInOneCloudFiles(files);
        }
        //Todo convert files list to dto
        return null;
    }

    public FilesListDto getGoogleFilesList() {
        var user = userService.getCurrentUser();
        var token = tokenControl.extractToken(user, CloudStorage.GOOGLE_DRIVE);

        if (token == null) {
            return null; //Todo change to exception or something else
        }

        ResponseEntity<GetFilesGoogleDrive> response = googleDriveRepository.getFiles(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<GoogleFile> files = response.getBody().getFiles();
            return filesListMapper.googleFilesToInOneCloudFiles(files);
        }


        //Todo convert files list to dto
        return null;
    }

}
