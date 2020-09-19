package me.inonecloud.service;

import me.inonecloud.clouds.dto.dropbox.DownloadFileDropboxRs;
import me.inonecloud.clouds.dto.google.DownloadFileGoogle;
import me.inonecloud.clouds.dto.yandex.YandexDownloadFile;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.repository.DropboxRepository;
import me.inonecloud.repository.GoogleDriveRepository;
import me.inonecloud.repository.YandexRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DownloadFilesService {
    private final DropboxRepository dropboxRepository;
    private final GoogleDriveRepository googleDriveRepository;
    private final YandexRepository yandexRepository;

    private final TokenControl tokenControl;
    private final UserService userService;

    public DownloadFilesService(DropboxRepository dropboxRepository, GoogleDriveRepository googleDriveRepository, YandexRepository yandexRepository, TokenControl tokenControl, UserService userService) {
        this.dropboxRepository = dropboxRepository;
        this.googleDriveRepository = googleDriveRepository;
        this.yandexRepository = yandexRepository;
        this.tokenControl = tokenControl;
        this.userService = userService;
    }


    public String downloadDropboxFile(String path) {
        var user = userService.getCurrentUser();
        var token = tokenControl.extractToken(user, CloudStorage.DROPBOX);

        ResponseEntity<DownloadFileDropboxRs> response = dropboxRepository.downloadFile(token, path);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody().getLink();
        }
        return "";
    }

    public String downloadYandexFile(String path){
        var user = userService.getCurrentUser();
        var token = tokenControl.extractToken(user, CloudStorage.YANDEX_DISK);

        ResponseEntity<YandexDownloadFile> response = yandexRepository.downloadFile(token, path);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody().getLink();
        }
        return "";
    }

    public String downloadGoogleFile(String fileId){
        var user = userService.getCurrentUser();
        var token = tokenControl.extractToken(user, CloudStorage.GOOGLE_DRIVE);

        ResponseEntity<DownloadFileGoogle> response = googleDriveRepository.downloadFile(token, fileId);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getWebContentLink();
        }
        return "";
    }
}
