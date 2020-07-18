package me.inonecloud.controller;

import me.inonecloud.domain.CloudStorage;
import me.inonecloud.service.FilesService;
import me.inonecloud.service.dto.FilesListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/clouds/files")
public class FilesController {
    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/{cloud}")
    public ResponseEntity<FilesListDto> getFiles(Principal principal, @PathVariable String cloud){
        if (cloud.equals(CloudStorage.DROPBOX.name().toLowerCase())){
            return new ResponseEntity<>(filesService.getDropboxFilesList(), HttpStatus.OK);
        }
        if(cloud.equals(CloudStorage.YANDEX_DISK.name().toLowerCase())){
            return new ResponseEntity<>(filesService.getYandexFilesList(), HttpStatus.OK);
        }
        if(cloud.equals(CloudStorage.GOOGLE_DRIVE.name().toLowerCase())){
            return new ResponseEntity<>(filesService.getGoogleFilesList(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
