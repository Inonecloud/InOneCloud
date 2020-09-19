package me.inonecloud.controller;

import me.inonecloud.domain.CloudStorage;
import me.inonecloud.service.DownloadFilesService;
import me.inonecloud.service.FilesService;
import me.inonecloud.service.dto.FilesListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/clouds/files")
public class FilesController {
    private final FilesService filesService;
    private final DownloadFilesService downloadFilesService;

    public FilesController(FilesService filesService, DownloadFilesService downloadFilesService) {
        this.filesService = filesService;
        this.downloadFilesService = downloadFilesService;
    }

    @GetMapping("/{cloud}")
    public ResponseEntity<FilesListDto> getFiles(@PathVariable CloudStorage cloud) {
        if (cloud.equals(CloudStorage.DROPBOX)) {
            return new ResponseEntity<>(filesService.getDropboxFilesList(), HttpStatus.OK);
        }
        if (cloud.equals(CloudStorage.YANDEX_DISK)) {
            return new ResponseEntity<>(filesService.getYandexFilesList(), HttpStatus.OK);
        }
        if (cloud.equals(CloudStorage.GOOGLE_DRIVE)) {
            return new ResponseEntity<>(filesService.getGoogleFilesList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{cloud}/download")
    public ResponseEntity<?> downloadFile(@PathVariable CloudStorage cloud, @RequestParam("path") String path) {
        if (cloud.equals(CloudStorage.DROPBOX)) {
            return ResponseEntity.ok(downloadFilesService.downloadDropboxFile(path));
        }
        if (cloud.equals(CloudStorage.YANDEX_DISK)){
            return ResponseEntity.ok(downloadFilesService.downloadYandexFile(path));
        }
        if (cloud.equals(CloudStorage.GOOGLE_DRIVE)){
            return ResponseEntity.ok(downloadFilesService.downloadGoogleFile(path));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
