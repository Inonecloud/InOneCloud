package me.inonecloud.controller;

import me.inonecloud.service.CloudsAuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/auth")
public class CloudsCodeController {

    private final CloudsAuthService yandexAuthService;
    private final CloudsAuthService dropboxAuthService;

    public CloudsCodeController(@Qualifier("yandexAuthService") CloudsAuthService cloudsAuthService,
                                @Qualifier("dropboxAuthService") CloudsAuthService dropboxAuthService) {
        this.yandexAuthService = cloudsAuthService;
        this.dropboxAuthService = dropboxAuthService;
    }

    @GetMapping("/yandex/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeYandexCode(Principal principal, @PathVariable String code) {
        yandexAuthService.getCode(code, principal.getName());
    }

    @GetMapping("/dropbox/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeDropboxCode(Principal principal, @PathVariable String code) {
        dropboxAuthService.getCode(code, principal.getName());
    }
}
