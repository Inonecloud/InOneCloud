package me.inonecloud.controller;

import me.inonecloud.service.CloudsAuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CloudsCodeController {

    private CloudsAuthService cloudsAuthService;

    public CloudsCodeController(@Qualifier("yandexAuthService") CloudsAuthService cloudsAuthService) {
        this.cloudsAuthService = cloudsAuthService;
    }

    @GetMapping("/auth/yandex/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void takeYandexCode(@PathVariable String code) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            cloudsAuthService.getCode(code, authentication.getName());

        }
    }
}
