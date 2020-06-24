package me.inonecloud.controller;

import me.inonecloud.domain.CloudStorage;
import me.inonecloud.service.FilesService;
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
    public void getFiles(Principal principal, @PathVariable String cloud){
        if (cloud.equals(CloudStorage.DROPBOX.name().toLowerCase())){
            filesService.getDropboxFilesList(principal.getName());
        }
        if(cloud.equals(CloudStorage.YANDEX_DISK.name().toLowerCase())){
            filesService.getYandexFilesList(principal.getName());
        }
        if(cloud.equals(CloudStorage.GOOGLE_DRIVE.name().toLowerCase())){
            filesService.getGoogleFilesList(principal.getName());
        }
    }

}
