package me.inonecloud.controller;

import me.inonecloud.service.CloudsAuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/auth")
public class CloudsCodeController {

    private final CloudsAuthService yandexAuthService;
    private final CloudsAuthService dropboxAuthService;
    private final CloudsAuthService googleAuthService;

    public CloudsCodeController(@Qualifier("yandexAuthService") CloudsAuthService cloudsAuthService,
                                @Qualifier("dropboxAuthService") CloudsAuthService dropboxAuthService,
                                @Qualifier("googleAuthService") CloudsAuthService googleAuthService) {
        this.yandexAuthService = cloudsAuthService;
        this.dropboxAuthService = dropboxAuthService;
        this.googleAuthService = googleAuthService;
    }

    @GetMapping("/yandex/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeYandexCode(@PathVariable String code) {
        yandexAuthService.getCode(code);
    }

    @GetMapping("/dropbox/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeDropboxCode(@PathVariable String code) {
        dropboxAuthService.getCode(code);
    }

    @GetMapping("/google/{first_code}/{second_code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeGoogleCode(@PathVariable("first_code") String fCode,
                               @PathVariable("second_code") String sCode){
        googleAuthService.getCode(fCode+"/"+sCode);
    }
}
