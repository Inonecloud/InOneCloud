package me.inonecloud.controller;

import me.inonecloud.service.CloudAuthStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/auth")
public class CloudsCodeController {

    private final CloudAuthStrategy cloudAuthStrategy;

    public CloudsCodeController(CloudAuthStrategy cloudAuthStrategy) {
        this.cloudAuthStrategy = cloudAuthStrategy;
    }

    @GetMapping("/yandex/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeYandexCode(@PathVariable String code) {
        cloudAuthStrategy
                .chooseCloudAuthService("YandexAuthService")
                .getCode(code);

    }

    @GetMapping("/dropbox/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeDropboxCode(@PathVariable String code) {
        cloudAuthStrategy
                .chooseCloudAuthService("DropboxAuthService")
                .getCode(code);
    }

    @GetMapping("/google/{first_code}/{second_code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeGoogleCode(@PathVariable("first_code") String fCode,
                               @PathVariable("second_code") String sCode) {
        cloudAuthStrategy
                .chooseCloudAuthService("GoogleAuthService")
                .getCode(fCode + "/" + sCode);

    }
}
