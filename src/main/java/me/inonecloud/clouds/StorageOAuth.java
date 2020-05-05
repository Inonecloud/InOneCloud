package me.inonecloud.clouds;

import me.inonecloud.service.CloudsAuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("storages")
public class StorageOAuth {
    private final CloudsAuthService cloudsAuthService;

    public StorageOAuth(@Qualifier("yandexAuthService") CloudsAuthService cloudsAuthService) {
        this.cloudsAuthService = cloudsAuthService;
    }

    @GetMapping("/yandexauth")
    public void yandexAuth(@RequestParam String code) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            cloudsAuthService.getCode(code, authentication.getName());
        }
    }
}
